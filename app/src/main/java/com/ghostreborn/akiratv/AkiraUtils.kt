package com.ghostreborn.akiratv

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentActivity.MODE_PRIVATE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class AkiraUtils {

    fun checkForUpdate(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val sp = context.getSharedPreferences(Constants.SHARED_PREF, MODE_PRIVATE)
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).apply { timeZone = TimeZone.getTimeZone("UTC") }
            val lastUpdate = if (!sp.getBoolean(Constants.PREF_SETUP_COMPLETE, false)) {
                val conn = URL("https://api.github.com/repos/TorqueReborn/AkiraTV/releases/latest").openConnection() as HttpURLConnection
                conn.requestMethod = "GET"
                val response = if (conn.responseCode == HttpURLConnection.HTTP_OK) conn.inputStream.bufferedReader().use { it.readText() } else ""
                JSONObject(response).getJSONArray("assets").getJSONObject(0).getString("updated_at").also {
                    sp.edit().putString(Constants.PREF_LAST_DATE, it).putBoolean(Constants.PREF_SETUP_COMPLETE, true).apply()
                }
            } else sp.getString(Constants.PREF_LAST_DATE, "") ?: ""

            val conn = URL("https://api.github.com/repos/TorqueReborn/AkiraTV/releases/latest").openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            val newTime = sdf.parse(JSONObject(conn.inputStream.bufferedReader().use { it.readText() })
                .getJSONArray("assets").getJSONObject(0).getString("updated_at")) as Date
            if (newTime.after(sdf.parse(lastUpdate) as Date)) withContext(Dispatchers.Main) {
                Toast.makeText(context, "Update Available", Toast.LENGTH_SHORT).show()
            }
        }
    }
}