package com.yuanqihudong.task.act

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.afollestad.materialdialogs.MaterialDialog
import com.yuanqihudong.task.databinding.ActNetBinding
import com.yuanqihudong.task.net.TaskClient
import com.yuanqihudong.task.net.Urls
import com.yuanqihudong.task.utils.ToolsUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class NetActivity : AppCompatActivity() {

    private lateinit var mBinding: ActNetBinding
    private var mLoadingDialog: MaterialDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActNetBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        mLoadingDialog = ToolsUtils.loadingDialog(this, "", "加载中...")

        mBinding.text.movementMethod = ScrollingMovementMethod.getInstance()

        mBinding.retrofitAsync.setOnClickListener { async() }
        mBinding.retrofitSync.setOnClickListener { sync() }
        mBinding.retrofitSuspendAsync.setOnClickListener { suspendAsync() }
    }

    private fun suspendAsync() {
        lifecycleScope.launchWhenResumed {
            kotlin.runCatching {
                mLoadingDialog?.show()
                TaskClient.getService(Urls::class.java).getArticleSuspendRetrofit()
            }.onFailure {
                mLoadingDialog?.dismiss()
                mBinding.text.text = it.message
            }.onSuccess {
                mLoadingDialog?.dismiss()
                mBinding.text.text = it.toString()
            }
        }
    }

    private fun async() {
        lifecycleScope.launch {
            TaskClient.getService(Urls::class.java).getArticleRetrofit()
                .enqueue(object : Callback<Any> {
                    override fun onResponse(call: Call<Any>, response: Response<Any>) {
                        if (response.isSuccessful) {
                            mBinding.text.text = response.body().toString()
                        } else {
                            mBinding.text.text = response.errorBody().toString()
                        }
                    }

                    override fun onFailure(call: Call<Any>, t: Throwable) {
                        mBinding.text.text = t.message
                    }
                })
        }
    }

    private fun sync() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val execute = TaskClient.getService(Urls::class.java).getArticleRetrofit().execute()
                withContext(Dispatchers.Main) {
                    mBinding.text.text = if (execute.isSuccessful) {
                        execute.body().toString()
                    } else {
                        execute.errorBody().toString()
                    }
                }
            } catch (e: IOException) {
                withContext(Dispatchers.Main) {
                    mBinding.text.text = e.message
                }
            }
        }
    }

}