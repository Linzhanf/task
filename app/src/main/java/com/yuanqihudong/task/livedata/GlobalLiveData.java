package com.yuanqihudong.task.livedata;

import com.yuanqihudong.task.livedata.test.SimpleLiveData;

/**
 * 全局livedata
 * 如果只是普通的MutableLiveData会存在粘性事件
 * 如果是修改了version的EasyLiveData则不会出现粘性事件
 */
public class GlobalLiveData {

    private static class Inner {
        static GlobalLiveData ins = new GlobalLiveData();
    }

    public static GlobalLiveData getInstance() {
        return Inner.ins;
    }

    private SimpleLiveData simpleLiveData;

    private GlobalLiveData() {
        simpleLiveData = new SimpleLiveData();
    }

    public SimpleLiveData getSimpleLiveData() {
        return simpleLiveData;
    }
}
