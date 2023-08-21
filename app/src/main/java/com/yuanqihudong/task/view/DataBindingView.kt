package com.yuanqihudong.task.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.yuanqihudong.task.R
import com.yuanqihudong.task.databinding.ViewDatabindingBinding
import com.yuanqihudong.task.utils.ToolsUtils

class DataBindingView : RelativeLayout {

    private lateinit var mBinding: ViewDatabindingBinding

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attr: AttributeSet?) : this(context, attr, 0)

    constructor(context: Context, attr: AttributeSet?, defAttr: Int) : super(
            context, attr, defAttr
    ) {
    }


}

















