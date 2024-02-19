package com.yuanqihudong.task.act

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.yuanqihudong.task.base.BaseActivity
import com.yuanqihudong.task.net.LoadState
import com.yuanqihudong.task.viewmodel.CoroutineVm

class CoroutineActivity : BaseActivity() {

    private var textSb by mutableStateOf("")
    private val mVm by viewModels<CoroutineVm>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent { Greet() }
        initOther()
    }

    @Preview
    @Composable
    fun Greet() {
        Column { Text(text = textSb) }
    }

    private fun initOther() {
        mVm.articleLoadState.observe(this) {
            when (it) {
                is LoadState.Loading -> {

                }
                is LoadState.Success -> {

                }
                is LoadState.Fail -> {
                    if (it.type == 1) {
                        toast("this is a abnormal type")
                    } else {
                        toast(it.message)
                    }
                }
            }
        }
        mVm.articleResult.observe(this) {

        }
        mVm.articleResultWithResult.observe(this) {

        }
        mVm.syncRequest()
        mVm.syncRequestWithResult()
    }

}

