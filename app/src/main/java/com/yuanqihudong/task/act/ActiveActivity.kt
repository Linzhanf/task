package com.yuanqihudong.task.act

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yuanqihudong.task.R

class ActiveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Greeting()
        }
    }

    var totalTime = 2 * 60 * 60L

    var countDownTimer: CountDownTimer = object : CountDownTimer(totalTime, 1000) {
        override fun onTick(millisUntilFinished: Long) {

        }

        override fun onFinish() {
        }
    }.start()

    @Preview
    @Composable
    private fun Greeting() {
        Column {
            method(str = "第一种") {
                val intent = Intent(this@ActiveActivity, PickerActivity::class.java)
                startActivity(intent)
            }
            method(str = "第二种") {
                val intent = with(Intent()) {
                    setClassName(
                        "com.yuanqihudong.task.act",
                        "com.yuanqihudong.task.act.PickerActivity"
                    )
                    this
                }
                startActivity(intent)
            }
            method(str = "第三种") {
                val intent = Intent().apply {
                    setClassName(
                        this@ActiveActivity,
                        "com.yuanqihudong.task.act.PickerActivity"
                    )
                }
                startActivity(intent)
            }
            method(str = "第四种") {
                val intent = Intent().apply {
                    component = ComponentName(
                        "com.yuanqihudong.task.act",
                        "com.yuanqihudong.task.act.PickerActivity"
                    )
                }
                startActivity(intent)
            }
            method(str = "第五种") {
                val intent = with(Intent()) {
                    addCategory(Intent.CATEGORY_DEFAULT)
                    addCategory("com.yuanqihudong.task.category.broad")
                    action = "com.yuanqihudong.task.action.broad"
                    this
                }
                startActivity(intent)
            }
        }
    }

    @Composable
    private fun method(str: String, function: () -> Unit) {
        Text(text = str, modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.LightGray)
            .padding(16.dp)
            .clickable {
                function.invoke()
            })
    }
}