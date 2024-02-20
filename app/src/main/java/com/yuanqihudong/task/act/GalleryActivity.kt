package com.yuanqihudong.task.act

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.yuanqihudong.task.R
import com.yuanqihudong.task.base.BaseActivity
import com.yuanqihudong.task.databinding.ActGalleryBinding
import com.yuanqihudong.task.gallery.GalleryLayoutManager
import kotlin.math.abs
import kotlin.math.round


class GalleryActivity : BaseActivity() {

    private lateinit var mBinding: ActGalleryBinding
    private val mAdapter = object : BaseQuickAdapter<Int, BaseViewHolder>(R.layout.item_gallery) {

        override fun convert(holder: BaseViewHolder, item: Int) {
            Glide.with(context).load("https://pic.hnchumeng.com/FrODG_bnIn5780MRXzel888SS6yG?imageslim").into(holder.getView(R.id.img))
            holder.setText(R.id.tv, item.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActGalleryBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        mBinding.rv.layoutManager = GalleryLayoutManager(HORIZONTAL).apply {
            attach(mBinding.rv)
            setCallbackInFling(true)
            setItemTransformer { _, item, fraction ->
                item.pivotX = item.width / 2.0F
                item.pivotY = item.height / 2.0F
                //val scale = 1 - 0.2F * abs(fraction)
                val scale = 1 + 0.3F * abs(1 - abs(fraction))
                item.scaleX = scale
                item.scaleY = scale
                item.alpha = 1 - 0.5F * abs(fraction)
                item.translationZ = if (abs(fraction) <= 0.5F) 10F else 5F

                Log.i("TAG", "onCreate: scale=$scale , fraction=$fraction")
            }
            setOnItemSelectedListener { _, _, position ->
                val item = mAdapter.getItem(position)
                Log.i("TAG", "onCreate: $item")
            }
        }
        mBinding.rv.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                outRect.left = 12
            }
        })
        mBinding.rv.adapter = mAdapter
        mAdapter.setList(arrayListOf<Int>().apply { for (i in 0..10) add(i) })
    }

}