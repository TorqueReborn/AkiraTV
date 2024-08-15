package com.ghostreborn.akiratv.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentActivity
import com.ghostreborn.akiratv.R
import com.ghostreborn.akiratv.fragment.PlayEpisodeFragment

class PlayActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        supportFragmentManager.beginTransaction()
            .replace(R.id.play_frame_layout, PlayEpisodeFragment())
            .commitNow()
    }
}