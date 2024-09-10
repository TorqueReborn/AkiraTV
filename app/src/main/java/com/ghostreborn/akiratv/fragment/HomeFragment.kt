package com.ghostreborn.akiratv.fragment

import android.os.Bundle
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import com.ghostreborn.akiratv.AnimePresenter
import com.ghostreborn.akiratv.models.Anime
import com.ghostreborn.akiratv.utils.AkiraUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : BrowseSupportFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        title = "Akira TV"
        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        headersState = HEADERS_DISABLED
        CoroutineScope(Dispatchers.IO).launch {
            val db = AkiraUtils().getDB(requireContext())
            val saved = db.savedEntryDao().getCurrent()
            val anime = ArrayList<Anime>()
            saved.forEach {
                anime.add(Anime(it.animeID, it.anime, it.progress, it.thumbnail))
            }
            withContext(Dispatchers.Main) {
                rowsAdapter.add(ListRow(ArrayObjectAdapter(AnimePresenter()).apply {
                    addAll(0, anime)
                }))
            }
            withContext(Dispatchers.Main) { adapter = rowsAdapter }
        }
    }
}