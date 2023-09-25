package com.yuanqihudong.task.act

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.widget.TextView
import androidx.activity.viewModels
import com.yuanqihudong.task.base.BaseActivity
import com.yuanqihudong.task.databinding.ActNewsBinding
import com.yuanqihudong.task.viewmodel.NewsViewModel


class SecondActivity : BaseActivity() {

    private lateinit var mBinding: ActNewsBinding
    private val mViewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActNewsBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)
        mBinding.content.movementMethod = ScrollingMovementMethod.getInstance()
        mBinding.loadBtn.setOnClickListener {

        }

    }

    infix fun TextView.addText(text: String) {
        this.text = "${this.text}$text\n"
    }
}