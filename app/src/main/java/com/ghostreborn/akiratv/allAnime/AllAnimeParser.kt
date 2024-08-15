package com.ghostreborn.akiratv.allAnime

import androidx.core.text.HtmlCompat
import com.ghostreborn.akiratv.model.Anime
import com.ghostreborn.akiratv.model.AnimeDetails
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

    fun animeDetails(animeId: String): AnimeDetails {
        val show = JSONObject(AllAnimeNetwork().animeDetails(animeId).toString())
            .getJSONObject("data")
            .getJSONObject("show")

        val description =
            HtmlCompat.fromHtml(show.getString("description"), HtmlCompat.FROM_HTML_MODE_COMPACT)
                .toString()
        val relatedShows = show.getJSONArray("relatedShows")

        var prequel = ""
        var sequel = ""

        for (i in 0 until relatedShows.length()) {
            relatedShows.getJSONObject(i).apply {
                when (getString("relation")) {
                    "prequel" -> prequel = getString("showId")
                    "sequel" -> sequel = getString("showId")
                }
            }
        }

        return AnimeDetails(
            name = show.getString("name"),
            thumbnail = show.getString("thumbnail"),
            description = description,
            banner = show.getString("banner"),
            prequel = prequel,
            sequel = sequel
        )
    }
}