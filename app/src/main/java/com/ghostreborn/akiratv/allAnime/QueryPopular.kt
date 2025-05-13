package com.ghostreborn.akiratv.allAnime

import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class QueryPopular {

    private fun query(): String {
        val variables = "\"type\":\"anime\", \"size\":20, \"dateRange\":1"
        val queryTypes = "\$type:VaildPopularTypeEnumType!, \$size:Int!, \$dateRange:Int"
        val query = "queryPopular(type:\$type, size:\$size, dateRange:\$dateRange){recommendations{anyCard{_id}}}"
        val url = URL("https://api.allanime.day/api?variables={$variables}&query=query($queryTypes){$query}")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.setRequestProperty("Referer", "https://allmanga.to")
        return connection.inputStream.bufferedReader().use { it.readText() }
    }


    fun queryPopular(): List<String> {
        val rawJSON = query()
        val ids = ArrayList<String>()
        val recommendations: JSONArray = JSONObject(rawJSON)
            .getJSONObject("data")
            .getJSONObject("queryPopular")
            .getJSONArray("recommendations")
        for(i in 0 until recommendations.length()) {
            ids.add(recommendations.getJSONObject(i).getJSONObject("anyCard").getString("_id"))
        }
        return ids
    }

}