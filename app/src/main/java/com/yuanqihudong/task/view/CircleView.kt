package com.yuanqihudong.task.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.core.content.ContextCompat
import com.yuanqihudong.task.R
import com.yuanqihudong.task.utils.ToolsUtils

class CircleView : View {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attr: AttributeSet?) : this(context, attr, 0)

    constructor(context: Context, attr: AttributeSet?, defAttr: Int) : super(
        context, attr, defAttr
    ) {
        init(context, attr, defAttr)
    }

    lateinit var mPaint: Paint
    var circleColor: Int = 0

    private fun init(context: Context, attr: AttributeSet?, defAttr: Int) {

        context.obtainStyledAttributes(attr, R.styleable.CircleView).apply {
            circleColor = getColor(
                R.styleable.CircleView_circle_color,
                ContextCompat.getColor(context, R.color.purple_500)
            )
            recycle()
        }

        mPaint = Paint().apply {
            color = circleColor
            strokeWidth = 5f
            style = Paint.Style.FILL
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        //宽
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)

        //高
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        // 设置wrap_content的默认宽 / 高值
        // 默认宽/高的设定并无固定依据,根据需要灵活设置
        // 类似TextView,ImageView等针对wrap_content均在onMeasure()对设置默认宽 / 高值有特殊处理,具体读者可以自行查看
        // 设置wrap_content的默认宽 / 高值
        // 默认宽/高的设定并无固定依据,根据需要灵活设置
        // 类似TextView,ImageView等针对wrap_content均在onMeasure()对设置默认宽 / 高值有特殊处理,具体读者可以自行查看
        val width = ToolsUtils.screenWidth(context) / 2
        val height = ToolsUtils.screenHeight(context) / 2

        if (layoutParams.width == WRAP_CONTENT && layoutParams.height == WRAP_CONTENT) {
            setMeasuredDimension(width, height)
        } else if (layoutParams.width == WRAP_CONTENT) {
            setMeasuredDimension(width, heightSize)
        } else if (layoutParams.height == WRAP_CONTENT) {
            setMeasuredDimension(widthSize, height)
        }
    }

    private val path = Path()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val withPaddingWith = width - paddingRight - paddingLeft
        val withPaddingHeight = height - paddingTop - paddingBottom

        val r = withPaddingWith.coerceAtMost(withPaddingHeight) / 2F
        canvas?.drawCircle(
            paddingStart + withPaddingWith / 2F,
            paddingTop + withPaddingHeight / 2F,
            r,
            mPaint
        )

        mPaint.color = Color.parseColor("#000444")
        path.lineTo(0F, 0F)
        path.lineTo(
            paddingStart + withPaddingWith / 2F,
            paddingTop + withPaddingHeight / 2F
        )
        path.lineTo(measuredWidth * 1F / 2, measuredHeight * 1F)
        path.lineTo(0F, measuredHeight * 1F)
        canvas?.drawPath(path, mPaint)
    }
}

















