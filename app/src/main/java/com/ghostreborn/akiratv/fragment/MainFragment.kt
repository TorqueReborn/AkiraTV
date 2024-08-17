package com.ghostreborn.akiratv.fragment

import android.content.Intent
import android.os.Bundle
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.OnItemViewClickedListener
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.Row
import androidx.leanback.widget.RowPresenter
import com.ghostreborn.akiratv.allAnime.AllAnimeParser
import com.ghostreborn.akiratv.model.Anime
import com.ghostreborn.akiratv.presenter.AnimePresenter
import com.ghostreborn.akiratv.ui.AnimeDetailsActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment : BrowseSupportFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        title = "Akira TV"
        headersState = HEADERS_DISABLED
        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        val fetchTasks = listOf(
            { AllAnimeParser().searchAnime("") },
            { AllAnimeParser().queryPopular() },
            { AllAnimeParser().randomRecommendations() }
        )
        CoroutineScope(Dispatchers.IO).launch {
            fetchTasks.forEach { fetchTask ->
                val list = fetchTask()
                withContext(Dispatchers.Main) {
                    rowsAdapter.add(ListRow(ArrayObjectAdapter(AnimePresenter()).apply {
                        addAll(0, list)
                    }))
                }
            }
            withContext(Dispatchers.Main) { adapter = rowsAdapter }
        }
        onItemViewClickedListener = MainClickListener()
    }

    inner class MainClickListener : OnItemViewClickedListener {
        override fun onItemClicked(
            itemViewHolder: Presenter.ViewHolder?,
            item: Any,
            rowViewHolder: RowPresenter.ViewHolder?,
            row: Row
        ) {
            allAnimeID = (item as Anime).id
            startActivity(Intent(requireContext(), AnimeDetailsActivity::class.java))
        }
    }

    companion object {
        var allAnimeID = ""
        var episodeUrl = ""
    }
}