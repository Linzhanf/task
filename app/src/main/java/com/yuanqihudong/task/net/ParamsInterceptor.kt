package com.yuanqihudong.task.net

import android.os.Build
import android.text.TextUtils
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Response

class ParamsInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()
        val theUrl = oldRequest.url
        var newUrl = theUrl.toString()

        var newRequestBuilder = oldRequest.newBuilder()
            .method(oldRequest.method, oldRequest.body)
            .headers(oldRequest.headers).apply {
                addHeader("t", System.currentTimeMillis().toString())
                addHeader("sn", "98a39c9")
            }

        val tempParams = theDefaultParams()
        if (oldRequest.method == "GET") {
            theUrl.queryParameterNames.forEach { tempParams[it] = theUrl.queryParameter(it) }
            var paramsLink = if (tempParams.isEmpty()) "" else "?"
            tempParams.entries.forEachIndexed { i, entry ->
                if (!entry.value.isNullOrEmpty()) paramsLink += "${entry.key}=${entry.value}"
                if (i != theDefaultParams().size) paramsLink += "&"
            }
            val baseUrl = theUrl.toString().substringBefore("?")
            newUrl = "$baseUrl$paramsLink"
        } else if (oldRequest.method == "POST") {
            val formBodyBuilder = FormBody.Builder()
            if (oldRequest.body is FormBody) {
                val oldFormBody = oldRequest.body as FormBody
                for (i in 0 until oldFormBody.size) {
                    tempParams[oldFormBody.name(i)] = oldFormBody.value(i)
                }
            }
            tempParams.forEach {
                if (!TextUtils.isEmpty(it.value)) {
                    formBodyBuilder.add(it.key, it.value!!)
                }
            }
            newRequestBuilder = newRequestBuilder.method(oldRequest.method, formBodyBuilder.build())
        }
        return chain.proceed(newRequestBuilder.url(newUrl).build())
    }

    private fun theDefaultParams() = HashMap<String, String?>().apply {
        this["os"] = "android"
        this["osVersion"] = Build.VERSION.RELEASE
        this["appid"] = "xchat"
        this["ispType"] = "4"
        this["netType"] = "2"
        this["model"] = "M2012K11AC"
        this["appVersion"] = "1.3.82"
        this["appCode"] = "10382000"
        this["deviceId"] = "2caf0692-9c33-4d9a-828f-6015c96f20a6"
        this["isSimulator"] = "0"
        this["channel"] = "ttxq"
        this["imei"] = "2caf0692-9c33-4d9a-828f-6015c96f20a6"
    }
}