package com.yuanqihudong.task.act

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.ViewModelFactoryDsl
import com.yuanqihudong.task.base.BaseActivity
import com.yuanqihudong.task.livedata.GlobalLiveData
import com.yuanqihudong.task.livedata.test.SimpleLiveData
import com.yuanqihudong.task.viewmodel.FirstCustomViewModel
import com.yuanqihudong.task.viewmodel.FirstViewModel
import kotlin.concurrent.thread

class ViewModelActivity : BaseActivity() {

    private var customViewModel: FirstCustomViewModel? = null
    private var myViewModel: FirstViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Greet() }
        customViewModel = ViewModelProvider(this)[FirstCustomViewModel::class.java]
        myViewModel = FirstViewModel()

    }

    @Preview
    @Composable
    fun Greet() {
        Column {
            Button(onClick = {
                customViewModel?.change("继承的ViewModel change")
                myViewModel?.change("我的ViewModel change")
            }) {
                Text(text = "viewModel")
            }
            Text(text = "${customViewModel?.name?.value}")
            Text(text = "${myViewModel?.name?.value}")
        }
    }

}

