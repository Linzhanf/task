package com.yuanqihudong.task.net

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object TaskClient {

    const val HOST_URL = "https://www.wanandroid.com"

    private val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
        override fun checkClientTrusted(
            chain: Array<out X509Certificate>?, authType: String?
        ) {
        }

        override fun checkServerTrusted(
            chain: Array<out X509Certificate>?, authType: String?
        ) {
        }

        override fun getAcceptedIssuers(): Array<X509Certificate> {
            return arrayOf()
        }
    })

    private val sslSocketFactory = SSLContext.getInstance("SSL").apply {
        init(null, trustAllCerts, SecureRandom())
    }.socketFactory

    val client = OkHttpClient.Builder()
        .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        .hostnameVerifier { _, _ -> true }
        .connectTimeout(30, TimeUnit.SECONDS) //设置超时时间
        .retryOnConnectionFailure(true)

    private val retrofit = Retrofit.Builder()
        .client(client.build())
        .baseUrl(HOST_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
        .build()


    infix fun <T> getService(service: Class<T>): T {
        return retrofit.create(service)
    }
}