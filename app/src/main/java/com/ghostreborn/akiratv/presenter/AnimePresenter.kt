package com.ghostreborn.akiratv.presenter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.ghostreborn.akiratv.R
import com.ghostreborn.akiratv.fragment.MainFragment
import com.ghostreborn.akiratv.model.Anime
import com.ghostreborn.akiratv.ui.AnimeDetailsActivity

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
                Glide.with(viewHolder.view.context)
                    .load(item.thumbnail)
                    .into(thumbnail)
                viewHolder.view.setOnClickListener {
                    MainFragment.allAnimeID = item.id
                    viewHolder.view.context.startActivity(
                        Intent(
                            viewHolder.view.context,
                            AnimeDetailsActivity::class.java
                        )
                    )
                }
            }
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {}
}