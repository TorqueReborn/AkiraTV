package com.ghostreborn.akiratv.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.leanback.widget.Presenter
import com.ghostreborn.akiratv.R

class EpisodePresenter : Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.episode_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        when (item) {
            is String -> {
                viewHolder.view.isFocusable = true
                viewHolder.view.isFocusableInTouchMode = true
                val title = viewHolder.view.findViewById<TextView>(R.id.episode_title)
                val titleText = "$item"
                title.text = titleText
            }
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {}
}