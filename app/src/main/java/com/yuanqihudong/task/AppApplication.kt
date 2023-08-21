package com.yuanqihudong.task

import android.content.Context
import android.net.http.HttpResponseCache
import com.opensource.svgaplayer.SVGACache
import com.opensource.svgaplayer.SVGAParser.Companion.shareParser
import com.opensource.svgaplayer.SVGASoundManager
import com.opensource.svgaplayer.utils.log.SVGALogger.setLogEnabled
import com.yuanqihudong.common.CommonApplication
import java.io.File
import java.io.IOException

class AppApplication : CommonApplication() {

    companion object {
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        svgaInit()
    }

    private fun svgaInit() {
        try {
            val cacheDir = File(cacheDir, "http")
            if (!cacheDir.exists()) {
                cacheDir.mkdirs()
            }
            HttpResponseCache.install(cacheDir, (1024 * 1024 * 128).toLong())
        } catch (e: IOException) {
            e.printStackTrace()
        }
        SVGACache.onCreate(this, SVGACache.Type.FILE)
        shareParser().init(this)
        setLogEnabled(BuildConfig.DEBUG)
    }

}