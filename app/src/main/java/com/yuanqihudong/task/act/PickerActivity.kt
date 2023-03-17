package com.yuanqihudong.task.act

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.lzy.imagepicker.ImagePicker
import com.lzy.imagepicker.bean.ImageItem
import com.lzy.imagepicker.ui.ImageGridActivity
import com.lzy.imagepicker.view.CropImageView
import com.yuanqihudong.common.glide.GlideImageLoader
import com.yuanqihudong.task.base.BaseActivity
import com.yuanqihudong.task.databinding.LayoutPickerBinding


class PickerActivity : BaseActivity() {

    private lateinit var mBinding: LayoutPickerBinding
    private lateinit var picResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = LayoutPickerBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        ImagePicker.getInstance().apply {
            imageLoader = GlideImageLoader() //设置图片加载器
            isShowCamera = true //显示拍照按钮
            isCrop = true //允许裁剪（单选才有效）
            isSaveRectangle = true //是否按矩形区域保存
            selectLimit = 9 //选中数量限制
            style = CropImageView.Style.RECTANGLE //裁剪框的形状
            focusWidth = 800 //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
            focusHeight = 800 //裁剪框的高度。单位像素（圆形自动取宽高最小值）
            outPutX = 1000 //保存文件的宽度。单位像素
            outPutY = 1000 //保存文件的高度。单位像素
        }

        picResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            it.data?.let { res ->
                val images =
                    res.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS) as ArrayList<ImageItem>
                Glide.with(this).load(images[0].path).into(mBinding.imageIv)
            }
        }

        mBinding.takeTv.setOnClickListener {
            picResult.launch(
                Intent(this, ImageGridActivity::class.java).apply {
                    putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true)
                }
            )
        }
        mBinding.selectTv.setOnClickListener {
            picResult.launch(
                Intent(this, ImageGridActivity::class.java)
            )
        }
    }


}