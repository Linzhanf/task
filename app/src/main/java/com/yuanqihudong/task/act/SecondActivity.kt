package com.yuanqihudong.task.act

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.yuanqihudong.task.databinding.ActNewsBinding
import com.yuanqihudong.task.viewmodel.AllIntent
import com.yuanqihudong.task.viewmodel.NewsViewModel
import com.yuanqihudong.task.viewmodel.TaskState
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext


class SecondActivity : AppCompatActivity() {

    private lateinit var mBinding: ActNewsBinding
    private val mViewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActNewsBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)
        mBinding.text.movementMethod = ScrollingMovementMethod.getInstance()
        mBinding.loadBtn.setOnClickListener {

        }
    }

    infix fun TextView.addText(text: String) {
        this.text = "${this.text}$text\n"
    }
}