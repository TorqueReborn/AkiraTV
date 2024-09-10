package com.ghostreborn.akiratv.models

import com.google.gson.annotations.SerializedName

data class AnimeEntry(
    @SerializedName("data")
    val `data`: Data
) {
    data class Data(
        @SerializedName("attributes")
        val attributes: Attributes,
        @SerializedName("id")
        val id: String
    ) {
        data class Attributes(
            @SerializedName("canonicalTitle")
            val canonicalTitle: String,
            @SerializedName("posterImage")
            val posterImage: PosterImage
        ) {
            data class PosterImage(
                @SerializedName("large")
                val large: String,
                @SerializedName("medium")
                val medium: String
            )
        }
    }
}