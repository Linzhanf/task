package com.yuanqihudong.task.act

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.yuanqihudong.task.databinding.LayoutCustomViewBinding

class CustomViewActivity : AppCompatActivity() {

    lateinit var mBinding: LayoutCustomViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = LayoutCustomViewBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)
    }

}