package com.ghostreborn.akiratv.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.leanback.app.SearchSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.ObjectAdapter
import com.ghostreborn.akiratv.allAnime.AllAnimeParser
import com.ghostreborn.akiratv.presenter.AnimePresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchFragment : SearchSupportFragment(), SearchSupportFragment.SearchResultProvider {

    private val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
    private val microphonePermission = 1025

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSearchResultProvider(this)
        performSearch("")
        requestMicrophonePermission()
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
            rowsAdapter.clear()
            val fetchTasks = listOf(
                { AllAnimeParser().searchAnime(query) },
                { AllAnimeParser().queryPopular() },
                { AllAnimeParser().randomRecommendations() }
            )
            fetchTasks.forEach { fetchTask ->
                val list = fetchTask()
                withContext(Dispatchers.Main) {
                    rowsAdapter.add(ListRow(ArrayObjectAdapter(AnimePresenter()).apply {
                        addAll(0, list)
                    }))
                }
            }
        }
    }

    private fun requestMicrophonePermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.RECORD_AUDIO),
                microphonePermission
            )
            Toast.makeText(
                requireContext(),
                "To enable voice search upon opening, grant permission",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
