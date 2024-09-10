package com.ghostreborn.akiratv.provider

import com.ghostreborn.akiratv.models.AnimeEntry
import com.ghostreborn.akiratv.models.Authentication
import com.ghostreborn.akiratv.models.Entry
import com.ghostreborn.akiratv.models.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface KitsuProvider {
    @POST("oauth/token")
    @FormUrlEncoded
    fun login(
        @Field("grant_type") grantType: String = "password",
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<Authentication>

    @GET("edge/users?filter[self]=true")
    fun user(
        @Header("Authorization") authHeader: String
    ): Call<User>

    @GET("edge/users/{id}/library-entries")
    fun ids(
        @Path("id") userId: String,
        @Query("page[offset]") num: Int,
        @Query("page[limit]") limit: Int = 50,
        @Query("fields[library-entries]") libraryFields: String = "id,status,progress"
    ): Call<Entry>

    @GET("edge/library-entries/{id}/anime")
    fun anime(
        @Path("id") entryID: String,
        @Query("fields[anime]") libraryFields: String = "canonicalTitle,posterImage"
    ): Call<AnimeEntry>
}