package com.yuanqihudong.task.act

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MediatorLiveData
import com.yuanqihudong.task.base.BaseActivity
import com.yuanqihudong.task.livedata.GlobalLiveData
import com.yuanqihudong.task.livedata.test.SimpleLiveData
import kotlin.concurrent.thread

class LiveDataActivity : BaseActivity() {

    private var textSb by mutableStateOf("")

    private lateinit var simpleLiveData: SimpleLiveData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Greet() }
        simpleLiveData = SimpleLiveData()
        simpleLiveData.getName()?.observe(this) {
            textSb += it
        }
        GlobalLiveData.getInstance().simpleLiveData.getName()?.observe(this) {
            toast(it)
        }
        /*simpleLiveData.getName()?.observeForever {
            textSb += it
        }*/
    }

    @Preview
    @Composable
    fun Greet() {
        Column {
            Button(onClick = {
                simpleLiveData.getName()?.value =
                    "just emmit the simple data  time : ${System.currentTimeMillis()}\n"
            }) {
                Text(text = "simple livedata emmit")
            }
            Button(onClick = {
                thread {
                    simpleLiveData.getName()
                        ?.postValue("emmit the simple data who on thread time : ${System.currentTimeMillis()}\n")
                }
            }) {
                Text(text = "simple livdata emmit on thread")
            }
            Button(onClick = {
                GlobalLiveData.getInstance().simpleLiveData.getName()?.value =
                    "just emmit the error global simple data  time : ${System.currentTimeMillis()}\n"
            }) {
                Text(text = "simple livedata emmit")
            }
            Text(text = textSb)
        }
    }

}

