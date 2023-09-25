package com.yuanqihudong.task.livedata;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.lang.reflect.Method;

/**
 * 修复粘性事件
 */
public class EasyLiveData<T> extends LiveData<T> {

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        super.observe(owner, new EasyObserver<>(observer));
    }

    @Override
    public void observeForever(@NonNull Observer<? super T> observer) {
        super.observeForever(new EasyObserver<>(observer));
    }

    class EasyObserver<R> implements Observer<R> {

        private Observer observer;
        private boolean shouldConsumeFirstNotify;

        public EasyObserver(Observer observer) {
            this.observer = observer;
            shouldConsumeFirstNotify = isNewLiveData(EasyLiveData.this);
        }

        @Override
        public void onChanged(R t) {
            //第一次进来，没有发生过数据变更，则后续的变更直接通知。
            if (shouldConsumeFirstNotify) {
                observer.onChanged(t);
            } else {
                //若是LiveData 之前就有数据变更，那么这一次的变更不处理
                shouldConsumeFirstNotify = true;
            }
        }

        private boolean isNewLiveData(LiveData liveData) {
            Class ldClass = LiveData.class;
            try {
                Method method = ldClass.getDeclaredMethod("getVersion");
                method.setAccessible(true);
                //获取版本
                int version = (int) method.invoke(liveData);
                //版本为-1，说明是初始状态，LiveData 还未发生过数据变更。
                return version == -1;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    }
}
