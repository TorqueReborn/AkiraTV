package com.ghostreborn.akiratv.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ghostreborn.akiratv.R
import com.ghostreborn.akiratv.fragment.MainFragment
import com.ghostreborn.akiratv.model.Anime
import com.ghostreborn.akiratv.ui.AnimeDetailsActivity

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
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, AnimeDetailsActivity::class.java)
            intent.putExtra("anime_id", anime.id)
            MainFragment.allAnimeID = anime.id
            holder.itemView.context.startActivity(intent)
        }
        holder.itemView.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                holder.itemView.animate().scaleX(1.1f).scaleY(1.1f).setDuration(200).start()
            } else {
                holder.itemView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200).start()
            }
        }
    }

}