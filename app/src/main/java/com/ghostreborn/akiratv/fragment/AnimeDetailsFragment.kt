package com.ghostreborn.akiratv.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.ghostreborn.akiratv.R
import com.ghostreborn.akiratv.allAnime.AllAnimeParser
import com.ghostreborn.akiratv.ui.EpisodesActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnimeDetailsFragment : Fragment() {

    private lateinit var animeThumbnail: ImageView
    private lateinit var animeBanner: ImageView
    lateinit var animeId: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.details_layout, container, false)
        animeThumbnail = view.findViewById(R.id.anime_thumbnail)
        animeBanner = view.findViewById(R.id.anime_banner)
        animeId = arguments?.getString("anime_id") ?: ""
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            val animeDetails = AllAnimeParser().animeDetails(animeId)
            withContext(Dispatchers.Main) {
                view.findViewById<TextView>(R.id.anime_name).text = animeDetails.name
                Glide.with(requireContext())
                    .load(animeDetails.banner)
                    .into(animeBanner)
                Glide.with(requireContext())
                    .load(animeDetails.thumbnail)
                    .into(animeThumbnail)
                view.findViewById<Button>(R.id.watch_button).setOnClickListener {
                    startActivity(Intent(requireContext(), EpisodesActivity::class.java))
                }
            }
        }
    }

}