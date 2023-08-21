package com.yuanqihudong.task.net.rx

import com.yuanqihudong.common.BuildConfig

abstract class CustomObserver<T : Any> : BaseObserver<T>() {

    override fun onError(throwable: Throwable) {
        if (BuildConfig.DEBUG) {
            throwable.printStackTrace()
        }
        super.onError(throwable)
    }

    override fun handleError(errorCode: Int, errorMsg: String?) {
        try {
            onError(errorCode, errorMsg)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun onNext(t: T) {
        try {
            onResponse(t)
        } catch (e: Exception) {
            e.printStackTrace()
            onError(e)
        }
    }

    override fun onComplete() {
        super.onComplete()
        onAfter()
    }

    fun onAfter() {}
    abstract fun onResponse(t: T)
    abstract fun onError(errorCode: Int, errorMsg: String?)
}