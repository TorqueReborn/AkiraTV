package com.ghostreborn.akiratv.fragment

import android.os.Bundle
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import com.ghostreborn.akiratv.allAnime.AllAnimeParser
import com.ghostreborn.akiratv.presenter.AnimePresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment: BrowseSupportFragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        title = "Akira TV"
        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        val animePresenter = AnimePresenter()
        val listRowAdapter = ArrayObjectAdapter(animePresenter)
        val header = HeaderItem(0, "Recently Updated")

        CoroutineScope(Dispatchers.IO).launch {
            val list = AllAnimeParser().searchAnime("")
            withContext(Dispatchers.Main) {
                list.forEach {
                    listRowAdapter.add(it)
                }
                rowsAdapter.add(ListRow(header, listRowAdapter))
                adapter = rowsAdapter
            }
        }
    }
}