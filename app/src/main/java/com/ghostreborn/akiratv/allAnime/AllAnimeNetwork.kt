package com.ghostreborn.akiratv.allAnime

import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.HttpURLConnection
import java.net.URL

class AllAnimeNetwork {
    private fun connectAllAnime(
        variables: String,
        queryTypes: String,
        query: String
    ): String {
        val url = URL("https://api.allanime.day/api?variables={$variables}&query=query($queryTypes){$query}")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.setRequestProperty("Referer", "https://allanime.to")
        return connection.inputStream.bufferedReader().use { it.readText() }
    }

    fun searchAnime(anime: String): String {
        val variables = "\"search\":{\"allowAdult\":false,\"allowUnknown\":false,\"query\":\"$anime\"},\"limit\":18,\"page\":1,\"translationType\":\"sub\",\"countryOrigin\":\"JP\""
        val queryTypes = "\$search:SearchInput,\$limit:Int,\$page:Int,\$translationType:VaildTranslationTypeEnumType,\$countryOrigin:VaildCountryOriginEnumType"
        val query = "shows(search:\$search,limit:\$limit,page:\$page,translationType:\$translationType,countryOrigin:\$countryOrigin){edges{_id,name,thumbnail}}"
        return connectAllAnime(variables, queryTypes, query)
    }

    fun queryPopular(): String{
        val variables = "\"type\":\"anime\",\"size\":20,\"dateRange\":1"
        val queryTypes = "\$type:VaildPopularTypeEnumType!,\$size:Int!,\$dateRange:Int"
        val query = "queryPopular(type:\$type,size:\$size,dateRange:\$dateRange){recommendations{anyCard{_id,name,thumbnail}}}"
        return connectAllAnime(variables, queryTypes, query)
    }
    fun randomRecommendations(): String{
        val variables = "\"format\":\"anime\""
        val queryTypes = "\$format:String!"
        val query = "queryRandomRecommendation(format:\$format){_id,name,thumbnail}"
        return connectAllAnime(variables, queryTypes, query)
    }

    fun animeDetails(id: String): String {
        val variables = "\"showId\":\"$id\""
        val queryTypes = "\$showId:String!"
        val query = "show(_id:\$showId){name,thumbnail,description,banner,relatedShows}"
        return connectAllAnime(variables, queryTypes, query)
    }

    fun episodes(id: String): String {
        val variables = "\"showId\":\"$id\""
        val queryTypes = "\$showId:String!"
        val query = "show(_id:\$showId){availableEpisodesDetail}"
        return connectAllAnime(variables, queryTypes, query)
    }

    fun episodeUrls(id: String, episode: String): String {
        val variables = "\"showId\":\"$id\",\"episode\":\"$episode\",\"translationType\":\"sub\""
        val queryTypes = "\$showId:String!,\$episode:String!,\$translationType:VaildTranslationTypeEnumType!"
        val query = "episode(showId:\$showId,episodeString:\$episode,translationType:\$translationType){sourceUrls}"
        return connectAllAnime(variables, queryTypes, query)
    }
}