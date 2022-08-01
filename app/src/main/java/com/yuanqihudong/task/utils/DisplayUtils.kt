package com.yuanqihudong.task.utils

import android.content.Context

object DisplayUtils {

    fun screenHeight(context: Context) = context.resources.displayMetrics.heightPixels

    fun screenWidth(context: Context) = context.resources.displayMetrics.widthPixels

}