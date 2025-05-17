package com.ghostreborn.akiratv.presenter

import android.content.Context
import android.view.ViewGroup
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.ghostreborn.akiratv.model.Anime

class CardPresenter: Presenter() {

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        mContext = parent.context
        val cardView = ImageCardView(mContext)
        cardView.isFocusable = true
        cardView.isFocusableInTouchMode = true
        cardView.setMainImageDimensions(150, 200)
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(
        viewHolder: ViewHolder,
        item: Any?
    ) {
        val anime = item as Anime
        val cardView = viewHolder.view as ImageCardView
        cardView.titleText = anime.title
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {}

}