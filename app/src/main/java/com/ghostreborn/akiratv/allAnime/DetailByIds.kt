package com.ghostreborn.akiratv.allAnime

import com.ghostreborn.akiratv.model.Anime
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class DetailByIds {

    private fun query(ids: String): String {
        val variables = "\"ids\":" + "[" + ids.substring(0, ids.length - 1) + "]"
        val queryTypes = "\$ids:[String!]!"
        val query = "showsWithIds(ids:\$ids){_id,name, englishName,thumbnail,lastEpisodeInfo,rating,status}"
        val url = URL("https://api.allanime.day/api?variables={$variables}&query=query($queryTypes){$query}")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.setRequestProperty("Referer", "https://allmanga.to")
        return connection.inputStream.bufferedReader().use { it.readText() }
    }

    fun details(idList: List<String>): ArrayList<Anime> {
        val ids = StringBuilder()
        for(id in idList) {
            ids.append("\"").append(id).append("\",")
        }
        val animes = ArrayList<Anime>()
        val rawJSON = query(ids.toString())
        val shows = JSONObject(rawJSON)
            .getJSONObject("data")
            .getJSONArray("showsWithIds")
        for(i in 0 until shows.length()) {
            val show = shows.getJSONObject(i)
            val id = show.getString("_id")
            var name = show.getString("englishName")
            if(name.equals("null")) {
                name = show.getString("name")
            }
            var thumbnail = show.getString("thumbnail")
            if(!thumbnail.contains("https")) {
                thumbnail = "https://wp.youtube-anime.com/aln.youtube-anime.com/$thumbnail"
            }
            animes.add(Anime(id, name, thumbnail))
        }
        return animes
    }

}