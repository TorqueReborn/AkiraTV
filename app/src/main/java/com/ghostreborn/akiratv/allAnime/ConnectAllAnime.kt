package com.ghostreborn.akiratv.allAnime

import com.ghostreborn.akiratv.model.Anime
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class ConnectAllAnime {

    private fun query(ids: String): String {
        val variables = "\"ids\":" + "[" + ids.substring(0, ids.length - 1) + "]"
        val queryTypes = "\$ids:[String!]!"
        val query = "showsWithIds(ids:\$ids){_id,name,englishName,thumbnail,banner,season}"
        val url = URL("https://api.allanime.day/api?variables={$variables}&query=query($queryTypes){$query}")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.setRequestProperty("Referer", "https://allmanga.to")
        return connection.inputStream.bufferedReader().readText()
    }

    fun connect(idList: List<String>): ArrayList<Anime> {
        val ids = StringBuilder()
        for(id in idList) {
            ids.append("\"").append(id).append("\",")
        }
        val anime = ArrayList<Anime>()
        val rawJSON = query(ids.toString())
        val shows = JSONObject(rawJSON)
            .getJSONObject("data")
            .getJSONArray("showsWithIds")
        for(i in 0 until shows.length()) {
            val show = shows.getJSONObject(i)
            val id = show.getString("_id")
            val season = show.getJSONObject("season")
            var name = show.getString("englishName")
            val banner = show.getString("banner")
            var thumbnail = show.getString("thumbnail")
            if(name.equals("null")) {
                name = show.getString("name")
            }
            if(!thumbnail.contains("https")) {
                thumbnail = "https://wp.youtube-anime.com/aln.youtube-anime.com/$thumbnail"
            }
            val desc = season.getString("quarter") + " | " + season.getString("year")
            anime.add(Anime(id,name, thumbnail, banner, desc))
        }
        return anime
    }
}