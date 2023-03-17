package com.yuanqihudong.common

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.widget.Toast
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.yuanqihudong.common.toast.ToastProxy
import com.yuanqihudong.task.glide.UnsafeOkHttpClient
import java.lang.reflect.Field
import java.lang.reflect.Method


open class CommonApplication : Application(), ImageLoaderFactory {

    companion object {
        @JvmStatic
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        setToast()
    }

    @SuppressLint("SoonBlockedPrivateApi")
    private fun setToast() {
        try {
            // 获得HOOK点
            val toast = Toast(this)
            val getService: Method = toast.javaClass.getDeclaredMethod("getService")
            getService.isAccessible = true
            val sService: Any = getService.invoke(toast)
            // 生成代理对象
            val toastProxy = ToastProxy()
            val proxyNotiMng: Any = toastProxy.newProxyInstance(this, sService)
            // 替换 sService
            val sServiceField: Field = Toast::class.java.getDeclaredField("sService")
            sServiceField.isAccessible = true
            sServiceField.set(sService, proxyNotiMng)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun newImageLoader() = ImageLoader.Builder(this)
        .okHttpClient {
            UnsafeOkHttpClient.getUnsafeOkHttpClient()?.newBuilder()?.build()!!
        }
        .crossfade(true)
        .build()
}