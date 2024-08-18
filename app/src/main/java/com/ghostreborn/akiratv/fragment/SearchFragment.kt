package com.ghostreborn.akiratv.fragment

import android.content.Intent
import android.os.Bundle
import androidx.leanback.app.SearchSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.ObjectAdapter
import androidx.leanback.widget.OnItemViewClickedListener
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.Row
import androidx.leanback.widget.RowPresenter
import com.ghostreborn.akiratv.allAnime.AllAnimeParser
import com.ghostreborn.akiratv.fragment.MainFragment.Companion.allAnimeID
import com.ghostreborn.akiratv.model.Anime
import com.ghostreborn.akiratv.presenter.AnimePresenter
import com.ghostreborn.akiratv.ui.AnimeDetailsActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchFragment : SearchSupportFragment(), SearchSupportFragment.SearchResultProvider {

    private val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSearchResultProvider(this)
    }

    override fun getResultsAdapter(): ObjectAdapter {
        return rowsAdapter
    }

    override fun onQueryTextChange(newQuery: String?): Boolean {
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        performSearch(query ?: "")
        return true
    }

    private fun performSearch(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val searchResults = AllAnimeParser().searchAnime(query)
            withContext(Dispatchers.Main) {
                val headerItem = HeaderItem("Search Results")
                val listRowAdapter = ArrayObjectAdapter(AnimePresenter())
                listRowAdapter.addAll(0, searchResults)
                rowsAdapter.add(ListRow(headerItem, listRowAdapter))
            }
        }
    }
}
