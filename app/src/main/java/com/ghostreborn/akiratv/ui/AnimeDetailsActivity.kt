package com.ghostreborn.akiratv.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.ghostreborn.akiratv.R
import com.ghostreborn.akiratv.fragment.AnimeDetailsFragment

class AnimeDetailsActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime_details)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.details_frame_layout, AnimeDetailsFragment())
                .commitNow()
        }
    }
}