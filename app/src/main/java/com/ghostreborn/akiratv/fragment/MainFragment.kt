package com.ghostreborn.akiratv.fragment

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextPaint
import androidx.leanback.app.BackgroundManager
import androidx.leanback.app.BrowseSupportFragment
import com.ghostreborn.akiratv.Util

class MainFragment : BrowseSupportFragment() {

    private lateinit var mBackgroundManager: BackgroundManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        headersState = HEADERS_DISABLED
        mBackgroundManager = BackgroundManager.getInstance(activity)
        mBackgroundManager.attach(requireActivity().window)

        updateBackground("One Piece")
    }


    private fun updateBackground(animeName: String) {
        val paint = TextPaint().apply {
            color = Color.WHITE
            textSize = 32f
            textAlign = Paint.Align.CENTER
            isAntiAlias = true
        }
        var imageBitmap: Bitmap? = null
        val drawable = object: Drawable() {
            init {
                Util().urlToBitmap(requireContext(), "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250") {bitmap ->
                    imageBitmap = bitmap
                    invalidateSelf()
                }
            }
            override fun draw(canvas: Canvas) {
                val bounds = bounds
                val width = bounds.width().toFloat()
                val height = bounds.height().toFloat()

                val rectPaint = Paint()
                rectPaint.color = Color.BLACK
                rectPaint.style = Paint.Style.FILL
                canvas.drawRect(0f, 0f, width, height, rectPaint)
                canvas.drawText(animeName, width / 8, height / 8, paint)

                val image = imageBitmap
                if (image != null) {
                    val imageX = width * 5 / 8
                    val imageY = height / 4
                    val imageWidth = width / 3
                    val imageHeight = height / 2
                    canvas.drawBitmap(
                        image,
                        null,
                        RectF(imageX, imageY, imageX + imageWidth, imageY + imageHeight),
                        null
                    )
                }
            }
            override fun setAlpha(alpha: Int) {}
            override fun setColorFilter(colorFilter: ColorFilter?) {}
            override fun getOpacity(): Int = android.graphics.PixelFormat.TRANSLUCENT
        }
        mBackgroundManager.drawable = drawable
    }

}