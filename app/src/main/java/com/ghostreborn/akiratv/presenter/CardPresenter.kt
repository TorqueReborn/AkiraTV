package com.ghostreborn.akiratv.presenter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.leanback.widget.Presenter
import coil.load
import com.ghostreborn.akiratv.R
import com.ghostreborn.akiratv.model.Anime

class CardPresenter: Presenter() {

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        mContext = parent.context
        val animeItem = LayoutInflater.from(mContext).inflate(R.layout.anime_item, parent, false)
        animeItem.isFocusable = true
        animeItem.isFocusableInTouchMode = true
        return ViewHolder(animeItem)
    }

    override fun onBindViewHolder(
        viewHolder: ViewHolder,
        item: Any?
    ) {
        val anime = item as Anime
        val animeImage = viewHolder.view.findViewById<ImageView>(R.id.anime_image)
        animeImage.load(anime.thumbnail)
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {}

}