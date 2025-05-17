package com.ghostreborn.akiratv.fragment

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.leanback.app.BackgroundManager
import androidx.leanback.app.BrowseSupportFragment

class MainFragment : BrowseSupportFragment() {

    private lateinit var mBackgroundManager: BackgroundManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        headersState = HEADERS_DISABLED
        mBackgroundManager = BackgroundManager.getInstance(activity)
        mBackgroundManager.attach(requireActivity().window)

        updateBackground()
    }

    private fun updateBackground() {
        val drawable = object: Drawable() {
            override fun draw(canvas: Canvas) {
                val paint = Paint()
                paint.color = Color.WHITE
                paint.style = Paint.Style.FILL
                canvas.drawRect(0f, 0f, bounds.width().toFloat(), bounds.height().toFloat(), paint)
            }
            override fun setAlpha(alpha: Int) {}
            override fun setColorFilter(colorFilter: ColorFilter?) {}
            override fun getOpacity(): Int = android.graphics.PixelFormat.TRANSLUCENT
        }
        mBackgroundManager.drawable = drawable
    }

}