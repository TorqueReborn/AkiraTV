package com.ghostreborn.akiratv.fragment

import android.os.Bundle
import androidx.leanback.app.BrowseSupportFragment

class MainFragment: BrowseSupportFragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        title = "Akira TV"
    }
}