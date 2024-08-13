package com.ghostreborn.akiratv.presenter

import android.view.ViewGroup
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.ghostreborn.akiratv.model.Anime

class AnimePresenter : Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {

        val cardView = ImageCardView(parent.context)

        cardView.isFocusable = true
        cardView.isFocusableInTouchMode = true
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        when (item) {
            is Anime -> {
                val cardView = viewHolder.view as ImageCardView

                cardView.titleText = item.name
                cardView.contentText = item.id

                val screenWidth = viewHolder.view.resources.displayMetrics.widthPixels
                val imageWidth = screenWidth * 0.2

                cardView.setMainImageDimensions(imageWidth.toInt(), imageWidth.toInt())
                Glide.with(viewHolder.view.context)
                    .load(item.thumbnail)
                    .centerCrop()
                    .into(cardView.mainImageView)
            }
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {}
}