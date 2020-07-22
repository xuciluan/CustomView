package com.raina.demo.myapplication

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat

class SportView(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : View(context) {

    constructor(context: Context, attributeSet: AttributeSet) : this(context, attributeSet, 0)
    constructor(context: Context) : this(context, null, 0)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val radius = dpToPx(context, 150f)
    private val padding = dpToPx(context, 30f)
    private val rect = RectF(
        padding.toFloat(),
        padding.toFloat(),
        padding.toFloat() + radius.toFloat(),
        padding.toFloat() + radius.toFloat()
    )

    private val metrics = Paint.FontMetrics()
    private val textRect = Rect()

    init {
        paint.apply {
            strokeCap = Paint.Cap.ROUND
            paint.color = Color.GRAY
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = dpToPx(context, 15f)
            paint.textSize = dpToPx(context, 30f)
        }
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawOval(rect, paint)
        paint.color = ResourcesCompat.getColor(resources, R.color.colorAccent, null)
        canvas?.drawArc(rect, -90f, 200f, false, paint)

        paint.style = Paint.Style.FILL
//        paint.getTextBounds("abcdf", 0, "abcdf".length, textRect)
//        canvas?.drawText(
//            "abcdf",
//            padding + radius / 2 - (textRect.right + textRect.left) / 2,
//            padding + radius / 2 - (textRect.bottom + textRect.top) / 2,
//            paint
//        )
        paint.getFontMetrics(metrics)
        canvas?.drawText(
            "abcdf",
            padding + radius / 2 ,
            padding + radius / 2 - (metrics.ascent + metrics.descent) / 2,
            paint
        )
    }
}