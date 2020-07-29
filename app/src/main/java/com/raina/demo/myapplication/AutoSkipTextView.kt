package com.raina.demo.myapplication

import android.content.Context
import android.graphics.*
import android.os.Build.VERSION_CODES.P
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * @Author: xuciluan
 * @Date : 2020/7/27
 * @Desc : 遇到图片自动换行的TextView
 * @Version :
 */
class AutoSkipTextView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0) : View(context,attributeSet,defStyleAttr) {

    private val IMAGE_WIDTH = dpToPx(getContext(), 80f)
    private val IMAGE_OFFSET = dpToPx(getContext(), 80f)

    var text =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean justo sem, sollicitudin in maximus a, vulputate id magna. Nulla non quam a massa sollicitudin commodo fermentum et est. Suspendisse potenti. Praesent dolor dui, dignissim quis tellus tincidunt, porttitor vulputate nisl. Aenean tempus lobortis finibus. Quisque nec nisl laoreet, placerat metus sit amet, consectetur est. Donec nec quam tortor. Aenean aliquet dui in enim venenatis, sed luctus ipsum maximus. Nam feugiat nisi rhoncus lacus facilisis pellentesque nec vitae lorem. Donec et risus eu ligula dapibus lobortis vel vulputate turpis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In porttitor, risus aliquam rutrum finibus, ex mi ultricies arcu, quis ornare lectus tortor nec metus. Donec ultricies metus at magna cursus congue. Nam eu sem eget enim pretium venenatis. Duis nibh ligula, lacinia ac nisi vestibulum, vulputate lacinia tortor."
    var cutWidth = FloatArray(10)
    var pic : Bitmap
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val fontMetrics = Paint.FontMetrics()
    var verticalOffset = 0f
    var start = 0


    init {
        pic =  getBitmap()
        paint.textSize = 50f
        paint.setColor(Color.BLACK)
        paint.getFontMetrics(fontMetrics)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawColor(Color.GRAY)
        canvas?.drawBitmap(pic,width - IMAGE_WIDTH,IMAGE_OFFSET,paint)

        verticalOffset = - fontMetrics.top
        val length = text.length

        while (start < length){
            val top = verticalOffset + fontMetrics.top
            val bottom = verticalOffset + fontMetrics.bottom

            var maxWidth = 0f
            if(top > IMAGE_OFFSET && top < IMAGE_OFFSET + IMAGE_WIDTH ||
                    bottom > IMAGE_OFFSET && bottom < IMAGE_OFFSET + IMAGE_WIDTH){
                maxWidth = width - IMAGE_WIDTH
            }else{
                maxWidth = width.toFloat()
            }

            val count = paint.breakText(text, start, length,true, maxWidth, cutWidth)
            canvas?.drawText(text,start,start + count, 15f,verticalOffset,paint)
            start += count
            verticalOffset += paint.fontSpacing
        }

//        Log.d("xcl","top = " + fontMetrics.top)
//        Log.d("xcl","bottom = " + fontMetrics.bottom)
//        canvas?.drawText(text,0f,-  fontMetrics.top, paint)
    }

    private fun getBitmap() : Bitmap{
        val options = BitmapFactory.Options().apply {
             inJustDecodeBounds = true
        }
        val bitmap = BitmapFactory.decodeResource(resources,R.drawable.people,options)
        options.inJustDecodeBounds = false
        options.outWidth = IMAGE_WIDTH.toInt()
        options.outHeight = IMAGE_WIDTH.toInt()
        return BitmapFactory.decodeResource(resources,R.drawable.people,options)
    }

}