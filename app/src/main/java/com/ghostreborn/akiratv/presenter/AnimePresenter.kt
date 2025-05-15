package com.ghostreborn.akiratv.presenter

import android.view.ViewGroup
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.ghostreborn.akiratv.model.Anime

class AnimePresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val cardView = ImageCardView(parent.context).apply {
            isFocusable = true
            isFocusableInTouchMode = true
            val width = 250
            val height = 375
            layoutParams = ViewGroup.LayoutParams(width, height)
            setMainImage(null, false)
        }
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any?) {
        val anime = item as Anime
        val cardView = viewHolder.view as ImageCardView
        cardView.mainImageView?.let {
            Glide.with(cardView.context)
                .load(anime.thumbnail)
                .apply(
                    RequestOptions()
                        .override(250, 375)
                        .centerCrop()
                        .transform(RoundedCorners(32))
                )
                .into(it)
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {

    }
}