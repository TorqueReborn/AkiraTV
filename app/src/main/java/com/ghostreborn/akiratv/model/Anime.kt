package com.ghostreborn.akiratv.model

import java.io.Serializable

data class Anime(
    var id: String,
    val name: String,
    val thumbnail: String,
) : Serializable