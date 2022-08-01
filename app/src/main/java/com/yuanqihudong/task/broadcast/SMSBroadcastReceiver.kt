package com.yuanqihudong.task.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.widget.Toast
import java.util.*

class SMSBroadcastReceiver : BroadcastReceiver() {

    companion object {
        const val SMS_ACTION = "android.provider.Telephony.SMS_RECEIVED"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            if (SMS_ACTION == intent.action) {
                //根据接收到的意图对象获取短信
                val smsMessage = getMessageFromIntent(intent)
                val sb = StringBuilder()
                if (smsMessage.isNotEmpty()) {
                    smsMessage.forEach {
                        sb.append("接收到了短信：\\n发件人是：${it.displayOriginatingAddress}" +
                                "\\n------短信内容-------\\n${it.displayMessageBody}")
                    }
                }
                /*context?.sendBroadcast(Intent("readSMS").apply {
                    putExtra("sms", sb.toString())
                })*/
                Toast.makeText(context, sb.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    //从意图获取短信对象的具体方法
    private fun getMessageFromIntent(intent: Intent?) = run {
        val bundle = intent?.extras
        val pdus = bundle?.get("pdus") as Array<*>
        val retMeMessage = Array<SmsMessage>(pdus.size) {
            SmsMessage.createFromPdu(pdus[it] as ByteArray?)
        }
        retMeMessage
    }
}