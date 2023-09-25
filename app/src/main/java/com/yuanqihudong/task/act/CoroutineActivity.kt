package com.yuanqihudong.task.act

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.afollestad.materialdialogs.MaterialDialog
import com.yuanqihudong.common.bean.Person
import com.yuanqihudong.task.base.BaseActivity
import com.yuanqihudong.task.net.LoadState
import com.yuanqihudong.task.utils.ToolsUtils
import com.yuanqihudong.task.viewmodel.CoroutineVm
import kotlinx.coroutines.runBlocking

class CoroutineActivity : BaseActivity() {

    private var textSb by mutableStateOf("")
    private val mVm by viewModels<CoroutineVm>()
    private var mLoadingDialog: MaterialDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mLoadingDialog = ToolsUtils.loadingDialog(this, "", "加载中...")

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
                is LoadState.Loading -> mLoadingDialog?.show()
                is LoadState.Success -> mLoadingDialog?.dismiss()
                is LoadState.Fail -> {
                    mLoadingDialog?.dismiss()
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

