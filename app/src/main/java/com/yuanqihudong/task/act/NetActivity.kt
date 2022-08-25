package com.yuanqihudong.task.act

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yuanqihudong.task.databinding.ActNetBinding
import com.yuanqihudong.task.net.TaskClient
import com.yuanqihudong.task.net.Urls
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetActivity : AppCompatActivity() {

    private lateinit var mBinding: ActNetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActNetBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        mBinding.retrofit.setOnClickListener { retrofit() }
    }

    private fun retrofit() {
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