package com.ghostreborn.akiratv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ghostreborn.akiratv.model.Anime

class AnimeAdapter(private val animes: ArrayList<Anime>) :
    RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    class AnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val animeImageView: ImageView = itemView.findViewById(R.id.card_thumbnail)
        val animeNameTextView: TextView = itemView.findViewById(R.id.card_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.anime_item, parent, false)
        return AnimeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return animes.size
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = animes[position]
        holder.animeNameTextView.text = anime.name
        Glide.with(holder.itemView.context).load(anime.thumbnail).into(holder.animeImageView)
        holder.itemView.isFocusable = true
        holder.itemView.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // Zoom in animation
                holder.itemView.animate().scaleX(1.1f).scaleY(1.1f).setDuration(200).start()
            } else {
                // Zoom outanimation
                holder.itemView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200).start()
            }
        }
    }

}