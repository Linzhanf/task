package com.yuanqihudong.task

import android.app.Application
import android.content.Context

class CommonApplication : Application() {

    companion object {
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}