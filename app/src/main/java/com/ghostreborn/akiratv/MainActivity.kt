package com.ghostreborn.akiratv

import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import coil.load

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val image = findViewById<ImageView>(R.id.test_image)
        image.load("https://s4.anilist.co/file/anilistcdn/media/anime/banner/21-wf37VakJmZqs.jpg")
    }
}