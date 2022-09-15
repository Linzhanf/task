package com.yuanqihudong.task.act

import android.content.*
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import com.yuanqihudong.task.broadcast.StaticsBroadcastReceiver

class BroadcastActivity : AppCompatActivity() {

    private var activeBroadcastReceiver: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activeBroadcastReceiver = object : BroadcastReceiver() {

            override fun onReceive(context: Context?, intent: Intent?) {
                Toast.makeText(this@BroadcastActivity, "动态注册成功", Toast.LENGTH_SHORT).show()
                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(context, CustomViewActivity::class.java))
                }, 5000)
            }
        }

        setContent {
            Column {
                Text(text = "打开静态broadcast", Modifier.clickable {
                    sendBroadcast(
                        Intent(this@BroadcastActivity, StaticsBroadcastReceiver::class.java).apply {
                            component = ComponentName(
                                this@BroadcastActivity,
                                StaticsBroadcastReceiver::class.java
                            )
                        }
                    )
                })

                Text(text = "打开动态broadcast", Modifier.clickable {
                    sendBroadcast(Intent().apply {
                        action = "activeBroadcastReceiver"
                    })
                })

                Text(text = "SMS broadcast", Modifier.clickable {

                })
            }
        }
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(activeBroadcastReceiver, IntentFilter().apply {
            addAction("activeBroadcastReceiver")
        })
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(activeBroadcastReceiver)
    }
}