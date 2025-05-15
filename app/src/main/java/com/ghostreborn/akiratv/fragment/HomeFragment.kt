package com.ghostreborn.akiratv.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ghostreborn.akiratv.R
import com.ghostreborn.akiratv.adapter.AnimeAdapter
import com.ghostreborn.akiratv.allAnime.DetailByIds
import com.ghostreborn.akiratv.allAnime.QueryPopular
import com.ghostreborn.akiratv.model.Anime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment: Fragment() {

    private lateinit var homeRecycler: RecyclerView
    private lateinit var animeAdapter: AnimeAdapter
    private lateinit var homeBanner: ImageView
    private lateinit var homeTitle: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeRecycler = view.findViewById(R.id.home_recycler)
        homeBanner = view.findViewById(R.id.home_banner)
        homeTitle = view.findViewById(R.id.home_title)

        CoroutineScope(Dispatchers.IO).launch {
            val anime = DetailByIds().details(QueryPopular().queryPopular())
            withContext(Dispatchers.Main) {
                animeAdapter = AnimeAdapter(anime, homeBanner, homeTitle)
                homeRecycler.adapter = animeAdapter
                homeRecycler.layoutManager = GridLayoutManager(requireContext(), 1,LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

    companion object {
        fun updateUI(context: Context, anime: Anime, imageView: ImageView, homeTitle: TextView) {
            homeTitle.text = anime.name
            Glide.with(context)
                .load(anime.thumbnail)
                .into(imageView)
        }
    }

}