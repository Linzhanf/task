package com.yuanqihudong.task.act

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.core.graphics.drawable.DrawableCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.gyf.immersionbar.ktx.immersionBar
import com.yuanqihudong.task.R
import com.yuanqihudong.task.base.BaseActivity
import com.yuanqihudong.task.databinding.ActVpBinding
import kotlin.math.abs

class ViewPagerActivity : BaseActivity() {

    private lateinit var mBinding: ActVpBinding
    private val mAdapter =
        object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_db_text) {

            override fun convert(holder: BaseViewHolder, item: String) {
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActVpBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        immersionBar {
            titleBar(mBinding.toolBar, false)
            statusBarDarkFont(true)
            transparentStatusBar()
            navigationBarColor(R.color.white)
        }

        mBinding.rv.adapter = mAdapter
        mAdapter.setList(arrayListOf<String>().apply { for (i in 0..100) add("") })

        mBinding.appbar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            val toolbarHeight = appBarLayout.totalScrollRange
            val dy = abs(verticalOffset)
            if (dy <= toolbarHeight) {
                val scale = dy / toolbarHeight.toFloat()
                val alpha = scale * 255
                mBinding.avatarIv.alpha = scale
                mBinding.nameTv.alpha = scale
                mBinding.ivClose.alpha = 1 - scale
                mBinding.ivCloseBlack.alpha = scale
//                mBinding.topLl.setBackgroundColor(Color.argb( alpha, 54, 161, 70));
            }
        }
    }

    private fun changeSVGTheme(color: Int): Drawable {
        val wrappedDrawable = DrawableCompat.wrap(resources.getDrawable(R.mipmap.arrow_left_white))
        DrawableCompat.setTint(wrappedDrawable, color)
        return wrappedDrawable
    }
}