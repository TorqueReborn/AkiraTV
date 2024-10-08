package com.ghostreborn.akiratv.fragment

import android.os.Bundle
import android.util.Log
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.OnItemViewClickedListener
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.Row
import androidx.leanback.widget.RowPresenter
import com.ghostreborn.akiratv.allAnime.AllAnimeParser
import com.ghostreborn.akiratv.presenter.EpisodePresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EpisodesFragment : BrowseSupportFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        headersState = HEADERS_DISABLED

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
                    rowsAdapter.add(ListRow(listRowAdapter))
                }
                adapter = rowsAdapter
                onItemViewClickedListener = EpisodeClickListener()
            }
        }
    }

    inner class EpisodeClickListener : OnItemViewClickedListener {
        override fun onItemClicked(
            itemViewHolder: Presenter.ViewHolder?,
            item: Any,
            rowViewHolder: RowPresenter.ViewHolder?,
            row: Row
        ) {
            animeEpisode = item as String
            ServerFragment()
                .show(childFragmentManager, "Select Server")
        }
    }

    companion object {
        var animeEpisode: String = ""
    }
}