package com.ghostreborn.akiratv

import android.app.Activity
import android.graphics.drawable.Drawable
import coil.imageLoader
import coil.request.ImageRequest
import coil.size.Scale

class Utils {

    suspend fun getDrawableFromUrl(activity: Activity, url: String): Drawable? {
        val request = ImageRequest.Builder(activity)
            .data(url)
            .scale(Scale.FILL)
            .build()
        val result = activity.imageLoader.execute(request)
        return result.drawable
    }

}