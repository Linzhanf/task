package com.yuanqihudong.task.act

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.yuanqihudong.task.base.BaseActivity
import com.yuanqihudong.task.utils.NdkUtils

class NdkActivity : BaseActivity() {

    private var textSb by mutableStateOf("")

    init {
        System.loadLibrary("myndk")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        textSb = NdkUtils.stringFromJNI()

        setContent { Greet() }
    }

    @Preview
    @Composable
    fun Greet() {
        Column { Text(text = textSb) }
    }

}

