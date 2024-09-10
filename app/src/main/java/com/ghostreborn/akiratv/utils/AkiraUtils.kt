package com.ghostreborn.akiratv.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

class AkiraUtils {

    fun getLatestUpdate(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val updateURL = JSONObject(URL("https://api.github.com/repos/TorqueReborn/AkiraTV/releases/latest").readText())
                .getJSONArray("assets").getJSONObject(0).getString("browser_download_url")
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Update Available", Toast.LENGTH_SHORT).show()
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(updateURL)))
            }
        }
    }
}