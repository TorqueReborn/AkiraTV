package com.ghostreborn.akiratv

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : FragmentActivity() {

    // TODO get created at of assets[0]. If its greater then current (set current time as latest time at first),
    //  there is an update.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            AkiraUtils().initialRun(this@MainActivity)
            val test =AkiraUtils().checkTimeIsGreaterThan(getSharedPreferences(Constants.SHARED_PREF, MODE_PRIVATE).getString(Constants.PREF_LAST_DATE, "") as String)
            withContext(Dispatchers.Main) {
                if (test){
                    Toast.makeText(this@MainActivity, "Update Available", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}