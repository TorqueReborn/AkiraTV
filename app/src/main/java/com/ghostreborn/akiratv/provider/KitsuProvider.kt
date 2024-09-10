package com.ghostreborn.akiratv.provider

import com.ghostreborn.akiratv.models.Authentication
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface KitsuProvider {
    @POST("oauth/token")
    @FormUrlEncoded
    fun login(
        @Field("grant_type") grantType: String = "password",
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<Authentication>
}