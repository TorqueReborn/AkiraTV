package com.ghostreborn.akiratv.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ghostreborn.akiratv.R
import com.ghostreborn.akiratv.adapter.AnimeAdapter
import com.ghostreborn.akiratv.allAnime.DetailByIds
import com.ghostreborn.akiratv.allAnime.QueryPopular
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeRecycler: RecyclerView = view.findViewById(R.id.home_recycler)

        Glide.with(requireContext())
            .load("https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250")
            .into(view.findViewById(R.id.home_banner))
        CoroutineScope(Dispatchers.IO).launch {
            val anime = DetailByIds().details(QueryPopular().queryPopular())
            withContext(Dispatchers.Main) {
                val adapter = AnimeAdapter(anime)
                homeRecycler.adapter = adapter
                homeRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
            }
        }
    }

}