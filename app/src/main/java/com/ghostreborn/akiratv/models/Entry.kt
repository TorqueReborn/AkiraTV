package com.ghostreborn.akiratv.models

import com.google.gson.annotations.SerializedName

data class Entry(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("meta")
    val meta: Meta
) {
    data class Data(
        @SerializedName("attributes")
        val attributes: Attributes,
        @SerializedName("id")
        val id: String
    ) {
        data class Attributes(
            @SerializedName("progress")
            val progress: String,
            @SerializedName("status")
            val status: String
        )
    }

    data class Meta(
        @SerializedName("count")
        val count: Int
    )
}