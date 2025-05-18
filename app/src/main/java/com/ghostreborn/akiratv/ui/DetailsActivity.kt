package com.ghostreborn.akiratv.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.ghostreborn.akiratv.R

class DetailsActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val intent = intent
        Log.e("TAG", intent.getStringExtra("ANIME_ID").toString())

    }
}