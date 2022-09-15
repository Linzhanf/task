package com.yuanqihudong.task

import android.content.Context
import com.yuanqihudong.common.CommonApplication

class AppApplication : CommonApplication() {

    companion object {
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}