package com.ghostreborn.akiratv.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ghostreborn.akiratv.R
import com.ghostreborn.akiratv.model.Anime

class AnimeAdapter(private val animeList: List<Anime>) :
    RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    class AnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val animeImage: ImageView = itemView.findViewById(R.id.anime_image)
        init {
            itemView.isFocusable = true
            itemView.isFocusableInTouchMode = true

            itemView.setOnFocusChangeListener { v, hasFocus ->
                if (hasFocus) {
                    v.animate().scaleX(1.2f).scaleY(1.2f).setDuration(200).start()
                } else {
                    v.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200).start()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.anime_item, parent, false)
        return AnimeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = animeList[position]
        holder.animeImage.load(anime.thumbnail)
    }

    override fun getItemCount(): Int {
        return animeList.size
    }
}