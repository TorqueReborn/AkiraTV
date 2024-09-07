package com.ghostreborn.akiratv

import android.os.Bundle
import androidx.fragment.app.FragmentActivity

class MainActivity : FragmentActivity() {

    // TODO get created at of assets[0]. If its greater then current (set current time as latest time at first),
    //  there is an update.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}