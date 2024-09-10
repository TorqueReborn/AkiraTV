package com.ghostreborn.akiratv

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.leanback.widget.Presenter
import coil.load
import com.ghostreborn.akiratv.models.Anime

class AnimePresenter : Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.anime_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        when (item) {
            is Anime -> {
                viewHolder.view.isFocusable = true
                viewHolder.view.isFocusableInTouchMode = true
                val title = viewHolder.view.findViewById<TextView>(R.id.card_title)
                val thumbnail = viewHolder.view.findViewById<ImageView>(R.id.card_thumbnail)
                title.text = item.name
                thumbnail.load(item.thumbnail)
            }
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {}
}