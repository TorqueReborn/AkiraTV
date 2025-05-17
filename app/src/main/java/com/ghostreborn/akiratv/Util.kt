package com.ghostreborn.akiratv

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

class Util {

    fun urlToBitmap(context: Context, url: String, callback: (Bitmap?) -> Unit) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            Handler(Looper.getMainLooper()).post {
                startConversion(context, url, callback)
            }
        } else {
            startConversion(context, url, callback)
        }
    }

    private fun startConversion(context: Context, url: String, callback: (Bitmap?) -> Unit) {
        Glide.with(context)
            .asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    callback(resource)
                }
                override fun onLoadCleared(placeholder: Drawable?) {
                    callback(null)
                }

            })
    }

}