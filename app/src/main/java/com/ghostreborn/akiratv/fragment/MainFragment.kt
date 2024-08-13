package com.ghostreborn.akiratv.fragment

import android.os.Bundle
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import com.ghostreborn.akiratv.allAnime.AllAnimeParser
import com.ghostreborn.akiratv.presenter.AnimePresenter
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

        CoroutineScope(Dispatchers.IO).launch {
            val list = AllAnimeParser().searchAnime("")
            val chunkSize = 4
            val chunkedLists = list.chunked(chunkSize)

            withContext(Dispatchers.Main) {
                rowsAdapter.clear()

                for (chunk in chunkedLists) {
                    val listRowAdapter = ArrayObjectAdapter(AnimePresenter())
                    chunk.forEach { item ->
                        listRowAdapter.add(item)
                    }
                    rowsAdapter.add(ListRow(listRowAdapter))
                }

                adapter = rowsAdapter
            }
        }
    }
}