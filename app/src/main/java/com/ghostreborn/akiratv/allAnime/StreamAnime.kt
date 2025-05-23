package com.ghostreborn.akiratv.allAnime

import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL


class StreamAnime {

    fun getEncryptedUrls(id: String, episode: String): ArrayList<String> {
        val variables = "\"showId\":\"$id\",\"episode\":\"$episode\",\"translationType\":\"sub\""
        val queryTypes =
            "\$showId:String!,\$episode:String!,\$translationType:VaildTranslationTypeEnumType!"
        val query =
            "episode(showId:\$showId,episodeString:\$episode,translationType:\$translationType){" +
                    "sourceUrls" +
                    "}"
        val url =
            "https://api.allanime.day/api?variables={$variables}&query=query($queryTypes){$query}"
        val connection = URL(url).openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.setRequestProperty("Referer", "https://allmanga.to")
        val rawJSON = connection.inputStream.bufferedReader().readText()
        val links = getAllLinks(decryptedUrls(rawJSON))
        return links
    }

    private fun decrypt(decrypt: String): String {
        val decryptedString = StringBuilder()
        var i = 0
        while (i < decrypt.length) {
            decryptedString.append((decrypt.substring(i, i + 2).toInt(16) xor 56).toChar())
            i += 2
        }
        return decryptedString.toString()
    }

    private fun decryptedUrls(rawJSON: String): ArrayList<String> {
        val decrypted = ArrayList<String>()
        try {
            val sourceUrls = JSONObject(rawJSON)
                .getJSONObject("data")
                .getJSONObject("episode")
                .getJSONArray("sourceUrls")
            for (i in 0..<sourceUrls.length()) {
                var sourceUrl = sourceUrls.getJSONObject(i).getString("sourceUrl")
                if (sourceUrl.contains("--")) {
                    sourceUrl = decrypt(sourceUrl.substring(2))
                    if (!sourceUrl.contains("fast4speed")) {
                        sourceUrl =
                            "https://allanime.day" + sourceUrl.replace("clock", "clock.json")
                        decrypted.add(sourceUrl)
                    }
                }
            }
        } catch (e: JSONException) {
            Log.e("TAG", e.toString())
        }
        return decrypted
    }

    private fun getAllLinks(links: ArrayList<String>): ArrayList<String> {
        val ret = ArrayList<String>()
        for (link in links) {
            try {
                var connection = URL(link).openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.setRequestProperty("Referer", "https://allmanga.to")
                val rawJSON = connection.inputStream.bufferedReader().readText()
                val l = JSONObject(rawJSON)
                    .getJSONArray("links")
                for(i in 0..<l.length()){
                    ret.add(l.getJSONObject(i).getString("link"))
                }
            } catch (e: Exception) {
                Log.e("TAG", e.toString())
            }
        }
        return ret
    }

}