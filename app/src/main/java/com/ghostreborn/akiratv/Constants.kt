package com.ghostreborn.akiratv

import com.ghostreborn.akiratv.provider.KitsuProvider
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Constants {
    val SHARED_PREF = "AKIRA_PREF"
    val PREF_USER_NAME = "USER_NAME"
    val PREF_USER_ID = "USER_ID"
    val PREF_TOKEN = "TOKEN"
    val PREF_REFRESH_TOKEN = "REFRESH_TOKEN"
    val PREF_LOGGED_IN = "LOGGED_IN"

    val api = Retrofit.Builder()
        .baseUrl("https://kitsu.io/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(KitsuProvider::class.java)
}