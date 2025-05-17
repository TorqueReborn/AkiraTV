package com.ghostreborn.akiratv.fragment

import android.os.Bundle
import androidx.leanback.app.BackgroundManager
import androidx.leanback.app.BrowseSupportFragment
import com.ghostreborn.akiratv.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment : BrowseSupportFragment() {
    private lateinit var mBackgroundManager: BackgroundManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBackgroundManager = BackgroundManager.getInstance(activity)
        mBackgroundManager.attach(requireActivity().window)
        updateBackground()
    }

    private fun updateBackground() {

        // Make image as drawable
        CoroutineScope(Dispatchers.IO).launch {
            val drawable = Utils().getDrawableFromUrl(
                requireActivity(),
                "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"
            )
            withContext(Dispatchers.Main) {
                if(drawable != null) {
                    mBackgroundManager.drawable = drawable
                }
            }
        }

    }

}