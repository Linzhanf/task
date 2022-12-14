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


class NewsActivity : AppCompatActivity() {

    private lateinit var mBinding: ActNewsBinding
    private val mViewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActNewsBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)
        mBinding.text.movementMethod = ScrollingMovementMethod.getInstance()
        mBinding.loadBtn.setOnClickListener {
            doLaunch {
                mViewModel.allIntent.send(AllIntent.GetArticle)
            }
        }

        lifecycleScope.launch(context = Dispatchers.Main) {
            mViewModel.state.collect {
                when (it) {
                    is TaskState.BeforeLoad -> mBinding.text addText "before load"
                    is TaskState.DoLoad -> mBinding.text addText "do load"
                    is TaskState.GetArticle -> mBinding.text addText it.content
                    is TaskState.AfterLoad -> mBinding.text addText "after load"
                    is TaskState.ErrorData -> mBinding.text addText it.msg
                }
            }
        }
    }

    private fun doLaunch(method: suspend CoroutineScope.() -> Unit) {
        GlobalScope.launch {
            method.invoke(this)
        }
    }

    infix fun TextView.addText(text: String) {
        this.text = "${this.text}$text\n"
    }
}