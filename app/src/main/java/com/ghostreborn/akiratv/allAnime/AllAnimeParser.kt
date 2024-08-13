package com.ghostreborn.akiratv.allAnime

import com.ghostreborn.akiratv.model.Anime
import org.json.JSONObject

class AllAnimeParser {
    fun searchAnime(anime: String): ArrayList<Anime> {
        return ArrayList<Anime>().apply {
            val edgesArray = JSONObject(AllAnimeNetwork().searchAnime(anime).toString())
                .getJSONObject("data")
                .getJSONObject("shows")
                .getJSONArray("edges")
            for (i in 0 until edgesArray.length()) {
                edgesArray.getJSONObject(i).let {
                    add(Anime(it.getString("_id"), it.getString("name"), it.getString("thumbnail")))
                }
            }
        }
    }
}