package com.ghostreborn.akiratv

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import com.ghostreborn.akiratv.model.Anime
import com.ghostreborn.akiratv.presenter.CardPresenter

class MainFragment: BrowseSupportFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        loadRows()
    }

    private fun setupUI() {
        headersState = HEADERS_HIDDEN
        isHeadersTransitionOnBackEnabled = true
        brandColor = ContextCompat.getColor(requireActivity(), R.color.brand_color)
    }

    private fun loadRows() {
        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        val cardPresenter = CardPresenter()

        val listRowAdapter = ArrayObjectAdapter(cardPresenter)
        listRowAdapter.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        listRowAdapter.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        listRowAdapter.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        listRowAdapter.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))

        val header = HeaderItem(0, "Anime")
        rowsAdapter.add(ListRow(header, listRowAdapter))
        adapter = rowsAdapter
    }

}