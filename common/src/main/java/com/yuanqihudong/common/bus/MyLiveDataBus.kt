package com.yuanqihudong.common.bus

import androidx.lifecycle.*
import java.util.concurrent.ConcurrentHashMap

object MyLiveDataBus {

    //    LiveDataBus.with<String>("TestLiveDataBus").postStickyData("测试！")
    //    LiveDataBus.with<String>("TestLiveDataBus").observerSticky(this, false) {}

    private val mStickyMap = ConcurrentHashMap<String, StickyLiveData<*>>()

    fun <T> with(eventName: String) : StickyLiveData<T> {
        var stickyLiveData = mStickyMap[eventName]
        if (stickyLiveData == null) {
            stickyLiveData = StickyLiveData<T>(eventName)
            mStickyMap[eventName] = stickyLiveData
        }
        return stickyLiveData as StickyLiveData<T>
    }

    class StickyLiveData<T>(private var eventName: String) : LiveData<T>() {

        var mLiveDataVersion = 0
        var mStickyData: T? = null

        fun setStickyData(stickyData: T) {
            mStickyData = stickyData
            setValue(stickyData)
        }

        fun postStickyData(stickyData: T) {
            mStickyData = stickyData
            postValue(stickyData)
        }

        override fun setValue(value: T) {
            mLiveDataVersion++
            super.setValue(value)
        }

        override fun postValue(value: T) {
            super.postValue(value)
        }

        fun observerSticky(owner: LifecycleOwner, sticky: Boolean, observer: Observer<in T>) {
            // 移除自己保存的StickyLiveData
            owner.lifecycle.addObserver(LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_DESTROY) {
                    mStickyMap.remove(eventName)
                }
            })
            super.observe(owner, StickyObserver(this, sticky, observer))
        }

        /**
         * 重写LiveData的observer，把传入的observer包装一下
         */
        override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
            observerSticky(owner,false, observer)
        }
    }

    class StickyObserver<T>(
        private val stickyLiveData: StickyLiveData<T>,
        private val sticky: Boolean,
        private val observer: Observer<in T>
    ) : Observer<T> {

        private var mLastVersion = stickyLiveData.mLiveDataVersion

        override fun onChanged(t: T) {
            if (mLastVersion >= stickyLiveData.mLiveDataVersion) {
                if (sticky && stickyLiveData.mStickyData != null) {
                    observer.onChanged(stickyLiveData.mStickyData!!)
                }
                return
            }
            observer.onChanged(t)
        }
    }
}