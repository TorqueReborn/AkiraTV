package com.ghostreborn.akiratv.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.ghostreborn.akiratv.R
import com.ghostreborn.akiratv.fragment.AnimeDetailsFragment

class AnimeDetailsActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime_details)

        val animeId = intent.getStringExtra("anime_id")
        val fragment = AnimeDetailsFragment()
        val bundle = Bundle()
        bundle.putString("anime_id", animeId)
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.details_frame_layout, fragment)
            .commitNow()

    }
}