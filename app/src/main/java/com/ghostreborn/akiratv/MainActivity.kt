package com.ghostreborn.akiratv

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ghostreborn.akiratv.adapter.AnimeAdapter
import com.ghostreborn.akiratv.allAnime.DetailsByIds
import com.ghostreborn.akiratv.allAnime.QueryPopular
import com.ghostreborn.akiratv.model.Anime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : FragmentActivity() {

    private lateinit var animeBanner: ImageView
    private lateinit var animeTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animeTitle = findViewById(R.id.anime_name)
        animeBanner = findViewById(R.id.anime_banner)

        val recycler = findViewById<RecyclerView>(R.id.anime_recycler)

        CoroutineScope(Dispatchers.IO).launch {
            val anime = DetailsByIds().details(QueryPopular().queryPopular())
            withContext(Dispatchers.Main) {
                val adapter = AnimeAdapter(anime, animeTitle, animeBanner)
                recycler.layoutManager =
                    LinearLayoutManager(baseContext, LinearLayoutManager.HORIZONTAL, false)
                recycler.adapter = adapter
            }
        }

    }

    companion object {
        fun changeDesc(anime: Anime, animeName: TextView, animeBanner: ImageView) {
            animeName.text = anime.name
            if(anime.banner == "null") {
                animeBanner.load(anime.thumbnail)
            } else{
                animeBanner.load(anime.banner)
            }
        }
    }
}