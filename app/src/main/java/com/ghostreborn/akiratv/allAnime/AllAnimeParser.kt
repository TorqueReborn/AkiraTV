package com.ghostreborn.akiratv.allAnime

import android.util.Log
import androidx.core.text.HtmlCompat
import com.ghostreborn.akiratv.model.Anime
import com.ghostreborn.akiratv.model.AnimeDetails
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class AllAnimeParser {
    fun searchAnime(anime: String): ArrayList<Anime> {
        return ArrayList<Anime>().apply {
            val edgesArray = JSONObject(AllAnimeNetwork().searchAnime(anime))
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

    fun queryPopular(): ArrayList<Anime>{
        return ArrayList<Anime>().apply {
            val recommendations = JSONObject(AllAnimeNetwork().queryPopular())
                .getJSONObject("data")
                .getJSONObject("queryPopular")
                .getJSONArray("recommendations")
            for (i in 0 until recommendations.length()) {
                recommendations.getJSONObject(i).getJSONObject("anyCard").let {
                    add(Anime(it.getString("_id"), it.getString("name"), it.getString("thumbnail")))
                }
            }
        }
    }
    fun randomRecommendations(): ArrayList<Anime>{
        return ArrayList<Anime>().apply {
            val recommendations = JSONObject(AllAnimeNetwork().randomRecommendations())
                .getJSONObject("data")
                .getJSONArray("queryRandomRecommendation")
            for (i in 0 until recommendations.length()) {
                recommendations.getJSONObject(i).let {
                    add(Anime(it.getString("_id"), it.getString("name"), it.getString("thumbnail")))
                }
            }
        }
    }

    fun animeDetails(animeId: String): AnimeDetails {
        val show = JSONObject(AllAnimeNetwork().animeDetails(animeId))
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

    fun episodes(id: String): ArrayList<ArrayList<String>> {
        val episodesArray = JSONObject(AllAnimeNetwork().episodes(id))
            .getJSONObject("data")
            .getJSONObject("show")
            .getJSONObject("availableEpisodesDetail")
            .getJSONArray("sub")
        val episodeList = ArrayList<String>().apply {
            for (i in episodesArray.length() - 1 downTo 0) {
                add(episodesArray.getString(i))
            }
        }
        return groupEpisodes(episodeList)
    }

    private fun groupEpisodes(episodeList: ArrayList<String>): ArrayList<ArrayList<String>> {
        val group = ArrayList<ArrayList<String>>()
        var startIndex = 0
        while (startIndex < episodeList.size) {
            val endIndex = (startIndex + 15).coerceAtMost(episodeList.size)
            group.add(ArrayList(episodeList.subList(startIndex, endIndex)))
            startIndex = endIndex
        }
        return group
    }

    private fun episodeUrls(id: String, episode: String): List<String> {
        val sourceUrls = JSONObject(AllAnimeNetwork().episodeUrls(id, episode))
            .getJSONObject("data")
            .getJSONObject("episode")
            .getJSONArray("sourceUrls")

        return (0 until sourceUrls.length())
            .map { sourceUrls.getJSONObject(it).getString("sourceUrl") }
            .filter { it.contains("--") }
            .map {
                val decrypted =
                    decryptAllAnimeServer(it.substring(2)).replace("clock", "clock.json")
                if (!decrypted.contains("fast4speed")) "https://allanime.day$decrypted" else ""
            }
            .filter { it.isNotEmpty() }
    }

    fun getSourceUrls(id: String?, episode: String?): List<String> {
        return episodeUrls(id!!, episode!!).flatMap { source ->
            try {
                val rawJSON = getJSON(source)
                if (rawJSON == "error") return@flatMap emptyList<String>()

                val linksArray = JSONObject(rawJSON).getJSONArray("links")
                List(linksArray.length()) { linksArray.getJSONObject(it).getString("link") }
            } catch (e: JSONException) {
                Log.e("TAG", "Error parsing JSON: ", e)
                emptyList()
            }
        }
    }

    private fun getJSON(url: String?): String {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url ?: return "{}")
            .header("Referer", "https://allanime.to")
            .header("Cipher", "AES256-SHA256")
            .header(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/121.0"
            )
            .build()

        return try {
            client.newCall(request).execute().use { response ->
                response.body?.string() ?: "NULL"
            }
        } catch (e: IOException) {
            Log.e("TAG", "Error fetching JSON: ", e)
            "{}"
        }
    }

    private fun decryptAllAnimeServer(decrypt: String): String {
        return buildString {
            for (i in decrypt.indices step 2) {
                val dec = decrypt.substring(i, i + 2).toInt(16)
                append((dec xor 56).toChar())
            }
        }
    }
}