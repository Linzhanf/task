package com.yuanqihudong.task.viewmodel

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.yuanqihudong.task.net.Urls
import kotlinx.coroutines.CoroutineExceptionHandler
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

open class BaseViewModel : ViewModel() {

    //向协程提供一个全局异常，用来处理异常UI
    fun <T> errorContext(err: (errorMessage: Throwable) -> Unit): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, e ->
            err.invoke(e)
        }
    }
}