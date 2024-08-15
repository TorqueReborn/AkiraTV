package com.ghostreborn.akiratv.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ghostreborn.akiratv.AnimeAdapter
import com.ghostreborn.akiratv.R
import com.ghostreborn.akiratv.allAnime.AllAnimeParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment : Fragment() {

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
        CoroutineScope(Dispatchers.IO).launch {
            val animes = AllAnimeParser().searchAnime("")
            withContext(Dispatchers.Main) {
                val recyclerView = view.findViewById<RecyclerView>(R.id.main_recycler_view)
                recyclerView.adapter = AnimeAdapter(animes)
                recyclerView.layoutManager = GridLayoutManager(requireContext(), 4)
            }
        }
    }

    companion object {
        var allAnimeID: String = ""
        var episodeUrl: String = ""
    }
}