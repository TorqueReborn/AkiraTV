package com.ghostreborn.akiratv.update

import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class GithubAPI {
    fun latestPackage(): String {
        val url = URL("https://api.github.com/repos/TorqueReborn/AkiraTV/releases")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        val rawJSON = connection.inputStream.bufferedReader().use { it.readText() }
        val downloadUrl = JSONArray(rawJSON)
            .getJSONObject(0)
            .getJSONArray("assets")
            .getJSONObject(0)
            .getString("browser_download_url")
        return downloadUrl.toString()
    }
}