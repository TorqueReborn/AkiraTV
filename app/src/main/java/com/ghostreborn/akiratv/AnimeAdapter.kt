package com.ghostreborn.akiratv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load

class AnimeAdapter(private val animeList: List<Anime>) :
    RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    // ViewHolder class to hold references to the views for each item
    class AnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val animeImage: ImageView = itemView.findViewById(R.id.anime_image)
        // Add other views from anime_item.xml if needed (e.g., TextView for title)
        // val animeTitle: TextView = itemView.findViewById(R.id.anime_title)
        init {
            // Make the item view focusable for Android TV
            itemView.isFocusable = true
            itemView.isFocusableInTouchMode = true

            // Optional: Add a focus change listener for visual feedback
            itemView.setOnFocusChangeListener { v, hasFocus ->
                if (hasFocus) {
                    // Apply a visual effect when focused (e.g., scale up)
                    v.animate().scaleX(1.1f).scaleY(1.1f).setDuration(200).start()
                } else {
                    // Revert the visual effect when focus is lost
                    v.animate().scaleX(1.0f).scaleY(1.0f).setDuration(200).start()
                }
            }

            // Optional: Add a click listener to handle item selection
            itemView.setOnClickListener {
                // Handle item click here (e.g., navigate to detail screen)
                // val position = adapterPosition
                // if (position != RecyclerView.NO_POSITION) {
                //     val clickedAnime = // Get anime item from list using position
                //     // Perform action with clickedAnime
                // }
            }
        }
    }

    // Called when RecyclerView needs a new ViewHolder of the given type to represent an item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        // Inflate the layout for a single list item
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.anime_item, parent, false)
        // You can set focusable properties here if needed, similar to your Presenter
        // itemView.isFocusable = true
        // itemView.isFocusableInTouchMode = true
        return AnimeViewHolder(itemView)
    }

    // Called by RecyclerView to display the data at the specified position
    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        // Get the data model based on position
        val anime = animeList[position]

        // Load the image into the ImageView using Coil
        holder.animeImage.load(anime.thumbnail) {
            // Optional: Add transformations, placeholders, error handling
            // placeholder(R.drawable.placeholder_image)
            // error(R.drawable.error_image)
        }

        // Optional: Bind other data to views
        // holder.animeTitle.text = anime.title
    }

    // Returns the total number of items in the data set held by the adapter.
    override fun getItemCount(): Int {
        return animeList.size
    }

    // Optional: Method to update the data in the adapter
    fun updateData(newList: List<Anime>) {
        // Consider using DiffUtil for more efficient updates
        // For simplicity, we just update the list and notify
        (this.animeList as MutableList).clear() // Clear existing data (if mutable list)
        (this.animeList as MutableList).addAll(newList) // Add new data
        notifyDataSetChanged() // Notify the adapter that the data has changed
    }
}