package com.yuanqihudong.task.fra

import android.content.pm.ProviderInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.ImageLoader
import coil.load
import com.bumptech.glide.Glide
import com.dreamliner.lib.frame.base.BaseCompatFragment
import com.yuanqihudong.task.R
import com.yuanqihudong.task.databinding.FraFragmentationBinding
import com.yuanqihudong.task.glide.UnsafeOkHttpClient
import okhttp3.OkHttpClient
import java.security.cert.Certificate

class FragmentationFragment : BaseCompatFragment() {

    companion object {
        @JvmStatic
        fun newInstance(): FragmentationFragment {
            val args = Bundle()
            val fragment = FragmentationFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutId() = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FraFragmentationBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    private lateinit var mBinding: FraFragmentationBinding

    override fun initViews(view: View?) {
        mBinding.coilTestIv.load("https://img-blog.csdnimg.cn/20210124002108308.png") {
            placeholder(R.mipmap.ic_launcher_round)
        }
        /*Glide.with(this).load("https://static.runoob.com/images/demo/demo2.jpg")
            .into(mBinding.coilTestIv)*/
    }
}