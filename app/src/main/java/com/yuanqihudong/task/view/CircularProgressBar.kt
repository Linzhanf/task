package com.yuanqihudong.task.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Paint.Cap
import android.graphics.Paint.Style.STROKE
import android.graphics.Shader.TileMode.CLAMP
import android.util.AttributeSet
import android.view.View
import com.yuanqihudong.task.R


class CircularProgressBar : View {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attr: AttributeSet?) : this(context, attr, 0)

    constructor(context: Context, attr: AttributeSet?, defAttr: Int) : super(context, attr, defAttr) {
        init(context, attr, defAttr)
    }

    private var mStartColor: Int = 0XFFA2DF
    private var mEndColor: Int = 0XFFA2DF
    private var mStrokeWidth = 8F

    private var mCurrentProgress = 50
    private var mMaxProgress = 100

    private fun init(context: Context, attrs: AttributeSet?, defAttr: Int) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CircularProgressBar)
        mStartColor = a.getColor(R.styleable.CircularProgressBar_cpb_start_color, -0x1)
        mEndColor = a.getColor(R.styleable.CircularProgressBar_cpb_end_color, -0x1)
        mCurrentProgress = a.getInt(R.styleable.CircularProgressBar_cpb_progress, 0)
        mMaxProgress = a.getInt(R.styleable.CircularProgressBar_cpb_max_progress, 0)
        mStrokeWidth = a.getDimension(R.styleable.CircularProgressBar_cpb_stroke_width, 0F)

        a.recycle()
    }

    private val progressPaint: Paint by lazy {
        Paint().apply {
            shader = LinearGradient(180F, 0F, 0F, 180F, mStartColor, mEndColor, CLAMP)
            style = STROKE
            strokeWidth = mStrokeWidth
            strokeCap = Cap.ROUND
            isAntiAlias = true
        }
    }


    override fun onDraw(canvas: Canvas) {
        val center = width / 2f
        val radius = center - progressPaint.strokeWidth / 2f
        val sweepAngle = 360f * mCurrentProgress / mMaxProgress
        canvas.drawArc(center - radius, center - radius, center + radius, center + radius,
            -90f, sweepAngle, false, progressPaint)
    }

    fun setProgress(progress: Int) {
        mCurrentProgress = progress
        invalidate()
    }

    fun setMaxProgress(max: Int) {
        mMaxProgress = max
    }
}
