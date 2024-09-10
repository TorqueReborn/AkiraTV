package com.ghostreborn.akiratv.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.room.Room
import com.ghostreborn.akiratv.Constants
import com.ghostreborn.akiratv.database.SavedEntryDatabase
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

    fun checkLogin(context: Context): Boolean {
        return context.getSharedPreferences(Constants.SHARED_PREF, MODE_PRIVATE)
            .getBoolean(Constants.PREF_LOGGED_IN, false)
    }

    fun getDB(context: Context): SavedEntryDatabase {
        return Room.databaseBuilder(
            context,
            SavedEntryDatabase::class.java, "my-database"
        ).build()
    }
}