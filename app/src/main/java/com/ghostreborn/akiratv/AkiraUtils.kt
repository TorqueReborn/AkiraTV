package com.ghostreborn.akiratv

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.fragment.app.FragmentActivity.MODE_PRIVATE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class AkiraUtils {

    fun checkForUpdate(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val assets = JSONObject(URL("https://api.github.com/repos/TorqueReborn/AkiraTV/releases/latest").readText())
                .getJSONArray("assets").getJSONObject(0)
            val sp = context.getSharedPreferences(Constants.SHARED_PREF, MODE_PRIVATE)
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).apply { timeZone = TimeZone.getTimeZone("UTC") }
            val lastUpdate = if (!sp.getBoolean(Constants.PREF_SETUP_COMPLETE, false)) {
                assets.getString("updated_at").also {
                    sp.edit().putString(Constants.PREF_LAST_DATE, it).putBoolean(Constants.PREF_SETUP_COMPLETE, true).apply()
                }
            } else sp.getString(Constants.PREF_LAST_DATE, "") ?: ""
            val newTime = sdf.parse(assets.getString("updated_at")) as Date
            if (newTime.after(sdf.parse(lastUpdate) as Date)) withContext(Dispatchers.Main) {
                Toast.makeText(context, "Update Available", Toast.LENGTH_SHORT).show()
                val updateURL = assets.getString("browser_download_url")
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(updateURL)))
            }
        }
    }
}