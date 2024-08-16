package com.ghostreborn.akiratv.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ghostreborn.akiratv.R
import com.ghostreborn.akiratv.adapter.ServerAdapter
import com.ghostreborn.akiratv.allAnime.AllAnimeParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ServerFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_server, container, false)

        val fragmentRecyclerView: RecyclerView = view.findViewById(R.id.server_recycler_view)
        val progressBar: ProgressBar = view.findViewById(R.id.server_progress_bar)

        fragmentRecyclerView.layoutManager = LinearLayoutManager(context)

        CoroutineScope(Dispatchers.IO).launch {
            val sources = AllAnimeParser().getSourceUrls(MainFragment.allAnimeID, EpisodesFragment.animeEpisode)
            withContext(Dispatchers.Main) {
                progressBar.visibility = View.GONE
                fragmentRecyclerView.adapter = ServerAdapter(sources)
            }
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
}