package com.ghostreborn.akiratv

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.ghostreborn.akiratv.fragment.LoginFragment
import com.ghostreborn.akiratv.utils.AkiraUtils

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(!AkiraUtils().checkLogin(this)){
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_frame, LoginFragment())
                .commit()
        }
    }
}