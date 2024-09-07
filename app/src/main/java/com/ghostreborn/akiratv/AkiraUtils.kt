package com.ghostreborn.akiratv

import android.content.Context
import androidx.fragment.app.FragmentActivity.MODE_PRIVATE
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class AkiraUtils {

    private fun connect(): StringBuilder {
        val out = StringBuilder()
        with(URL("https://api.github.com/repos/TorqueReborn/AkiraTV/releases/latest").openConnection() as HttpURLConnection) {
            requestMethod = "GET"
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream.bufferedReader().use { out.append(it.readText()) }
            }
        }
        return out
    }

    fun initialRun(context: Context) {
        val setupComplete = context.getSharedPreferences(Constants.SHARED_PREF, MODE_PRIVATE)
            .getBoolean(Constants.PREF_SETUP_COMPLETE, false)

        if (!setupComplete) {
            val lastUpdateDate = AkiraUtils().releaseDate()
            context.getSharedPreferences(Constants.SHARED_PREF, MODE_PRIVATE)
                .edit()
                .putString(Constants.PREF_LAST_DATE, lastUpdateDate)
                .putBoolean(Constants.PREF_SETUP_COMPLETE, true)
                .apply()
        }
    }

    private fun releaseDate(): String {
        val rawJSON = connect()
        return JSONObject(rawJSON.toString()).getJSONArray("assets")
            .getJSONObject(0)
            .getString("updated_at")
    }

    fun checkTimeIsGreaterThan(currentTime: String): Boolean {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val parsedCurrentTime = dateFormat.parse(currentTime) as Date
        val parsedNewTime = dateFormat.parse(releaseDate()) as Date
        return parsedNewTime.after(parsedCurrentTime)
    }

}