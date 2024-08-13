package com.ghostreborn.akiratv.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.ghostreborn.akiratv.R
import com.ghostreborn.akiratv.model.Anime

class AnimePresenter : Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.anime_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        when (item) {
            is Anime -> {
                val imageCard = viewHolder.view as LinearLayout
                imageCard.isFocusable = true
                val title = imageCard.findViewById<TextView>(R.id.card_title)
                val thumbnail = imageCard.findViewById<ImageView>(R.id.card_thumbnail)
                title.text = item.name
                Glide.with(viewHolder.view.context)
                    .load(item.thumbnail)
                    .into(thumbnail)
            }
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {}
}