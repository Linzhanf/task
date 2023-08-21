package com.yuanqihudong.task.act

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import com.afollestad.materialdialogs.MaterialDialog
import com.yuanqihudong.task.base.BaseActivity
import com.yuanqihudong.task.databinding.ActNetBinding
import com.yuanqihudong.task.net.retrofit.ApiService
import com.yuanqihudong.task.utils.ToolsUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class NetActivity : BaseActivity() {

    private lateinit var mBinding: ActNetBinding
    private var mLoadingDialog: MaterialDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActNetBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        mLoadingDialog = ToolsUtils.loadingDialog(this, "", "加载中...")

        mBinding.content.movementMethod = ScrollingMovementMethod.getInstance()

        mBinding.retrofitAsync.setOnClickListener { async() }
        mBinding.retrofitSync.setOnClickListener { sync() }
        mBinding.retrofitSuspendAsync.setOnClickListener { suspendAsync() }
        mBinding.flowSuspendAsync.setOnClickListener { flowAsync() }
    }

    private fun flowAsync() {
        lifecycleScope.launchWhenResumed {
            flow {
                emit(ApiService.getArticleSuspendRetrofit())
            }.onStart {
                mLoadingDialog?.show()
            }.onCompletion {
                mLoadingDialog?.dismiss()
            }.collect {
                mBinding.content.text = it.toString()
            }
        }
    }

    private fun suspendAsync() {
        lifecycleScope.launchWhenResumed {
            kotlin.runCatching {
                mLoadingDialog?.show()
                ApiService.getArticleSuspendRetrofit()
            }.onFailure {
                mLoadingDialog?.dismiss()
                mBinding.content.text = it.message
            }.onSuccess {
                mLoadingDialog?.dismiss()
                mBinding.content.text = it.toString()
            }
        }
    }

    private fun async() {
        lifecycleScope.launch {
            ApiService.getArticleRetrofit().enqueue(object : Callback<Any> {
                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    if (response.isSuccessful) {
                        mBinding.content.text = response.body().toString()
                    } else {
                        mBinding.content.text = response.errorBody().toString()
                    }
                }

                override fun onFailure(call: Call<Any>, t: Throwable) {
                    mBinding.content.text = t.message
                }
            })
        }
    }

    private fun sync() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val execute = ApiService.getArticleRetrofit().execute()
                withContext(Dispatchers.Main) {
                    mBinding.content.text = if (execute.isSuccessful) {
                        execute.body().toString()
                    } else {
                        execute.errorBody().toString()
                    }
                }
            } catch (e: IOException) {
                withContext(Dispatchers.Main) {
                    mBinding.content.text = e.message
                }
            }
        }
    }

}