package com.ghostreborn.akiratv.allAnime

import com.ghostreborn.akiratv.model.Anime
import org.json.JSONArray
import org.json.JSONObject

class QueryPopular {

    fun queryPopular(): ArrayList<Anime> {
        val variables = "\"type\":\"anime\", \"size\":20, \"dateRange\":1"
        val queryTypes = "\$type:VaildPopularTypeEnumType!, \$size:Int!, \$dateRange:Int"
        val query = "queryPopular(type:\$type, size:\$size, dateRange:\$dateRange){recommendations{anyCard{_id,name,thumbnail}}}"
        val rawJSON = AllAnimeHelper().query(variables, queryTypes, query)
        val anime = ArrayList<Anime>()
        val recommendations: JSONArray = JSONObject(rawJSON)
            .getJSONObject("data")
            .getJSONObject("queryPopular")
            .getJSONArray("recommendations")
        for(i in 0 until recommendations.length()) {
            val anyCard = recommendations.getJSONObject(i).getJSONObject("anyCard")
            anime.add(Anime(
                    anyCard.getString("name"),
                anyCard.getString("thumbnail")
                ))
        }
        return anime
    }
}