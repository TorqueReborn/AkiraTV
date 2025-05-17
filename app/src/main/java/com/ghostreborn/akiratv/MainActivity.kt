package com.ghostreborn.akiratv

import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val image = findViewById<ImageView>(R.id.test_image)
        image.load("https://s4.anilist.co/file/anilistcdn/media/anime/banner/21-wf37VakJmZqs.jpg")
        val recycler = findViewById<RecyclerView>(R.id.main_recycler)
        val animes = ArrayList<Anime>()
        animes.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        animes.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        animes.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        animes.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        animes.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        animes.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        animes.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        animes.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        animes.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        animes.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        animes.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        animes.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        animes.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        animes.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        animes.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        animes.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        animes.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        animes.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        animes.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        animes.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        animes.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        animes.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        animes.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        animes.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        animes.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        animes.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        animes.add(Anime("One Piece", "https://wp.youtube-anime.com/s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx21-YCDoj1EkAxFn.jpg?w=250"))
        val adapter = AnimeAdapter(animes)
        recycler.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.HORIZONTAL, false)
        recycler.adapter = adapter
    }
}