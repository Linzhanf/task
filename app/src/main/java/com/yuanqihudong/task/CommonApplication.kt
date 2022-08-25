package com.yuanqihudong.task

import android.app.Application
import android.content.Context
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.util.CoilUtils
import com.yuanqihudong.task.glide.UnsafeOkHttpClient
import okhttp3.OkHttpClient

class CommonApplication : Application(), ImageLoaderFactory {

    companion object {
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    override fun newImageLoader() = ImageLoader.Builder(this)
        .okHttpClient {
            UnsafeOkHttpClient.getUnsafeOkHttpClient()?.newBuilder()?.build()!!
        }
        .crossfade(true)
        .build()
}