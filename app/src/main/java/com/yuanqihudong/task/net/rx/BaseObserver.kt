package com.yuanqihudong.task.net.rx

import android.net.ParseException
import com.google.gson.JsonParseException
import com.yuanqihudong.task.net.bean.BusinessException
import com.yuanqihudong.task.net.bean.ErrorCode.*
import io.reactivex.rxjava3.observers.DisposableObserver
import org.json.JSONException
import retrofit2.HttpException
import java.io.IOException
import java.io.InterruptedIOException
import java.net.UnknownHostException

abstract class BaseObserver<T> : DisposableObserver<T>() {

    override fun onError(throwable: Throwable) {
        when (throwable) {
            is BusinessException -> {
                val (errorCode, errorMessage) = throwable
                handleError(errorCode, errorMessage)
            }

            is IOException -> {
                when (throwable) {
                    is UnknownHostException ->
                        handleError(INTERRUPTED_IO_EXCEPTION, "网络异常，请稍后重试")//服务器异常
                    is InterruptedIOException ->
                        handleError(INTERRUPTED_IO_EXCEPTION, "网络异常，请稍后重试") //超时异常
                    else -> handleError(OTHER_IO_EXCEPTION, "网络异常，请稍后重试")
                }
            }

            is HttpException ->
                handleError(HTTP_EXCEPTION, "网络异常，请稍后重试")//retrofit请求木有返回
            is JsonParseException, is JSONException, is ParseException ->
                handleError(EXCHANGE_DATA_ERROR, "解释数据错误")//解释数据错误
            else -> handleError(UNKNOWN_EXCEPTION, "网络异常，请稍后重试")
        }
    }

    override fun onComplete() {}
    abstract fun handleError(errorCode: Int, errorMsg: String?)
}