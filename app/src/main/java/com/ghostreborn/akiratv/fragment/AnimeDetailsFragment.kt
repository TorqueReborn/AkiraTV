package com.ghostreborn.akiratv.fragment

import android.os.Bundle
import androidx.leanback.app.DetailsSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.ClassPresenterSelector
import androidx.leanback.widget.DetailsOverviewRow
import androidx.leanback.widget.FullWidthDetailsOverviewRowPresenter
import com.ghostreborn.akiratv.model.Anime
import com.ghostreborn.akiratv.presenter.DetailsDescriptionPresenter

class AnimeDetailsFragment : DetailsSupportFragment() {

    private lateinit var mPresenterSelector: ClassPresenterSelector
    private lateinit var mAdapter: ArrayObjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenterSelector = ClassPresenterSelector()
        mAdapter = ArrayObjectAdapter(mPresenterSelector)
        setupDetailsOverviewRowPresenter()
        adapter = mAdapter
    }

    private fun setupDetailsOverviewRowPresenter() {
        val row = DetailsOverviewRow(Anime("ReooPAxPMsHM4KPMY", "One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        mAdapter.add(row)
        val detailsPresenter = FullWidthDetailsOverviewRowPresenter(DetailsDescriptionPresenter())
        mPresenterSelector.addClassPresenter(DetailsOverviewRow::class.java, detailsPresenter)
    }
}