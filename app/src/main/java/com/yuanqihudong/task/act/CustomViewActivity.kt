package com.yuanqihudong.task.act

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.yuanqihudong.task.base.BaseActivity
import com.yuanqihudong.task.databinding.LayoutCustomViewBinding
import com.yuanqihudong.task.view.RotateGestureDetector


class CustomViewActivity : BaseActivity() {

    lateinit var mBinding: LayoutCustomViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = LayoutCustomViewBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        /*RotateGestureDetector(mBinding.circle).setCycle(true)
            .setStartAngle(0)
            .setEndAngle(360)
            .setOnRotateListener { angle, _, _ -> mBinding.circle.rotation = angle.toFloat() }*/
    }

}