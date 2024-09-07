package com.ghostreborn.akiratv

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : FragmentActivity() {

    // TODO get created at of assets[0]. If its greater then current (set current time as latest time at first),
    //  there is an update.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            val test = AkiraUtils().checkTimeIsGreaterThan("0-0-0T0:0:0Z")
            println(test)
        }

    }
}