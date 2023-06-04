package com.yuanqihudong.task.act

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.yuanqihudong.task.base.BaseActivity
import com.yuanqihudong.task.databinding.LayoutCustomViewBinding
import com.yuanqihudong.task.utils.DragViewUtil

class CustomViewActivity : BaseActivity() {

    lateinit var mBinding: LayoutCustomViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = LayoutCustomViewBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)
        DragViewUtil.registerDragAction(mBinding.circle)
    }

}