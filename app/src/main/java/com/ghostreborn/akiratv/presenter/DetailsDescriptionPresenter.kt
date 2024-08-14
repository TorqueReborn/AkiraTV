package com.ghostreborn.akiratv.presenter

import androidx.leanback.widget.AbstractDetailsDescriptionPresenter
import com.ghostreborn.akiratv.model.Anime

class DetailsDescriptionPresenter: AbstractDetailsDescriptionPresenter() {
    override fun onBindDescription(vh: ViewHolder, item: Any) {
        val anime = item as Anime
        vh.title.text = anime.name
        vh.subtitle.text = anime.id
        vh.body.text = anime.thumbnail
    }
}