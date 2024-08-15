package com.ghostreborn.akiratv

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ghostreborn.akiratv.ui.PlayActivity

class ServerAdapter (private val sourceUrls: List<String>) :
    RecyclerView.Adapter<ServerAdapter.AnimeViewHolder>() {
    class AnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val serverTextView: TextView = itemView.findViewById(R.id.server_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.server_list, parent, false)
        return AnimeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return sourceUrls.size
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val server = sourceUrls[position]
        holder.serverTextView.text = server
        holder.serverTextView.setOnClickListener {
            val intent = Intent(holder.itemView.context, PlayActivity::class.java)
            intent.putExtra("sourceUrl", server)
            holder.itemView.context.startActivity(intent)
        }
    }

}