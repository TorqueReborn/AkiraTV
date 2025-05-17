package com.ghostreborn.akiratv.allAnime

import java.net.HttpURLConnection
import java.net.URL

class AllAnimeHelper {

    fun query(variables: String, queryTypes: String, query: String): String {
        val url = URL("https://api.allanime.day/api?variables={$variables}&query=query($queryTypes){$query}")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.setRequestProperty("Referer", "https://allmanga.to")
        return connection.inputStream.bufferedReader().use { it.readText() }
    }
}