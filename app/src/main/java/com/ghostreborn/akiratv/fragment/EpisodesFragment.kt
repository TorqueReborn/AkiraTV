package com.ghostreborn.akiratv.fragment

import android.os.Bundle
import android.util.Log
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import com.ghostreborn.akiratv.allAnime.AllAnimeParser
import com.ghostreborn.akiratv.presenter.EpisodePresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EpisodesFragment : BrowseSupportFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        CoroutineScope(Dispatchers.IO).launch {
            val list = AllAnimeParser().episodes(MainFragment.allAnimeID)

            Log.e("TAG", list.toString())

            withContext(Dispatchers.Main) {
                for (i in 0 until list.size) {
                    val listRowAdapter = ArrayObjectAdapter(EpisodePresenter())
                    for (j in 0 until list[i].size) {
                        listRowAdapter.add(list[i][j])
                    }
                    val header = HeaderItem(i.toLong(), "Page ${i + 1}")
                    rowsAdapter.add(ListRow(header, listRowAdapter))
                }
                adapter = rowsAdapter
            }
        }
    }
}