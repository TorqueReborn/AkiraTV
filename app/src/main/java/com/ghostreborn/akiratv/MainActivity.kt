package com.ghostreborn.akiratv

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.ghostreborn.akiratv.fragment.SearchFragment

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_browse_fragment, SearchFragment())
            .commitNow()
    }
}