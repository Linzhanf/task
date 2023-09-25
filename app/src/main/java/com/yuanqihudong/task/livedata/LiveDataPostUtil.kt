package com.yuanqihudong.task.livedata

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData

object LiveDataPostUtil {

    private var handler: Handler? = null

    fun <T> postValue(liveData: MutableLiveData<T>?, data: T) {
        if (liveData == null || data == null) return
        if (handler == null) {
            handler = Handler(Looper.getMainLooper())
        }
        handler?.post {
            liveData.value = data
        }
    }
}