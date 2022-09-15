package com.yuanqihudong.common

import android.app.Application
import android.content.Context
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.jeremyliao.liveeventbus.LiveEventBus
import com.yuanqihudong.task.glide.UnsafeOkHttpClient

open class CommonApplication : Application(), ImageLoaderFactory {

    companion object {
        @JvmStatic
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