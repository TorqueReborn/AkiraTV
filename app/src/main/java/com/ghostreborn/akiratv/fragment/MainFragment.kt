package com.ghostreborn.akiratv.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ghostreborn.akiratv.adapter.AnimeAdapter
import com.ghostreborn.akiratv.R
import com.ghostreborn.akiratv.allAnime.AllAnimeParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutInflater
            .from(requireContext())
            .inflate(R.layout.main_layout, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchButton = view.findViewById<Button>(R.id.anime_search_button)
        val searchEditText = view.findViewById<EditText>(R.id.anime_search_edit)
        recyclerView = view.findViewById(R.id.main_recycler_view)

        searchAnime("")
        searchButton.setOnClickListener {
            searchAnime(searchEditText.text.toString())
        }
    }

    private fun searchAnime(name: String){
        CoroutineScope(Dispatchers.IO).launch {
            val anime = AllAnimeParser().searchAnime(name)
            withContext(Dispatchers.Main) {
                recyclerView.adapter = AnimeAdapter(anime)
                recyclerView.layoutManager = GridLayoutManager(requireContext(), 4)
            }
        }
    }

    companion object {
        var allAnimeID: String = ""
        var episodeUrl: String = ""
    }
}