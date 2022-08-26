package com.yuanqihudong.task.utils

import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog

object ToolsUtils {

    fun screenHeight(context: Context) = context.resources.displayMetrics.heightPixels

    fun screenWidth(context: Context) = context.resources.displayMetrics.widthPixels

    fun loadingDialog(context: Context, title: String, content: String) = run {
        val dialog: MaterialDialog =
            MaterialDialog.Builder(context).title(title).content(content).progress(true, 0)
                .cancelable(false).build()
        dialog
    }
}

