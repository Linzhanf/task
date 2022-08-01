package com.yuanqihudong.task.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Toast
import kotlin.concurrent.thread

class StaticsBroadcastReceiver : BroadcastReceiver() {

    private val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            if (msg.what == 0x11) {
                Toast.makeText(mContext, "静态注册成功" + Thread.currentThread(), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private var mContext: Context? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        mContext = context
        Toast.makeText(context, "静态注册成功" + Thread.currentThread(), Toast.LENGTH_SHORT).show()
        thread {
            Thread.sleep(5000)
            handler.sendEmptyMessage(0x11)
        }
    }
}