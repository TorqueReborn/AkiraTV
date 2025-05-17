package com.ghostreborn.akiratv

import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ghostreborn.akiratv.adapter.AnimeAdapter
import com.ghostreborn.akiratv.allAnime.QueryPopular
import com.ghostreborn.akiratv.model.Anime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : FragmentActivity() {

    private lateinit var animeBanner: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animeBanner = findViewById(R.id.anime_banner)

        animeBanner.load("https://s4.anilist.co/file/anilistcdn/media/anime/banner/21-wf37VakJmZqs.jpg")
        val recycler = findViewById<RecyclerView>(R.id.anime_recycler)

        CoroutineScope(Dispatchers.IO).launch {
            val animes = QueryPopular().queryPopular()
            withContext(Dispatchers.Main) {
                val adapter = AnimeAdapter(animes, animeBanner)
                recycler.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.HORIZONTAL, false)
                recycler.adapter = adapter
            }
        }

    }

    companion object {
        fun changeDesc(anime: Anime, animeBanner: ImageView) {
            animeBanner.load(anime.thumbnail)
        }
    }
}