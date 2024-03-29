package com.yuanqihudong.task.act

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.trello.rxlifecycle4.android.ActivityEvent
import com.yuanqihudong.task.base.BaseActivity
import com.yuanqihudong.task.bean.WanArticleBean.DataDTO
import com.yuanqihudong.task.databinding.ActNewsBinding
import com.yuanqihudong.task.net.retrofit.TaskClient
import com.yuanqihudong.task.net.retrofit.Urls
import com.yuanqihudong.task.net.rx.CustomObserver
import com.yuanqihudong.task.net.rx.RxUtil.handleResultOnMain
import com.yuanqihudong.task.viewmodel.AllIntent
import com.yuanqihudong.task.viewmodel.NewsViewModel
import com.yuanqihudong.task.viewmodel.TaskState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class NewsActivity : BaseActivity() {

    private lateinit var mBinding: ActNewsBinding
    private val mViewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActNewsBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)
        mBinding.content.movementMethod = ScrollingMovementMethod.getInstance()
        mBinding.loadBtn.setOnClickListener {
            doLaunch {
                mViewModel.allIntent.send(AllIntent.GetArticle)
            }
        }

        lifecycleScope.launch(context = Dispatchers.Main) {
            mViewModel.state.collect {
                when (it) {
                    is TaskState.BeforeLoad -> mBinding.content addText "before load"
                    is TaskState.DoLoad -> mBinding.content addText "do load"
                    is TaskState.GetArticle -> mBinding.content addText it.content
                    is TaskState.AfterLoad -> mBinding.content addText "after load"
                    is TaskState.ErrorData -> mBinding.content addText it.msg
                }
            }
        }

        TaskClient.getService(Urls::class.java).getArticleRx()
            .compose(handleResultOnMain())
            .compose(bindUntilEvent(ActivityEvent.DESTROY))
            .subscribe(object : CustomObserver<DataDTO>() {
                override fun onResponse(t: DataDTO) {
                    mBinding.content addText t.toString()
                }

                override fun onError(errorCode: Int, errorMsg: String?) {
                    mBinding.content addText "$errorCode:$errorMsg"
                }
            })
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