package com.ghostreborn.akiratv.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.ghostreborn.akiratv.R
import com.ghostreborn.akiratv.fragment.EpisodesFragment

class EpisodesActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episodes)
        supportFragmentManager.beginTransaction()
            .replace(R.id.episodes_frame_layout, EpisodesFragment())
            .commitNow()
    }
}