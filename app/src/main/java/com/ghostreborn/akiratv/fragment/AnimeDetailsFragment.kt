package com.ghostreborn.akiratv.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import coil.load
import com.ghostreborn.akiratv.R
import com.ghostreborn.akiratv.allAnime.AllAnimeParser
import com.ghostreborn.akiratv.ui.AnimeDetailsActivity
import com.ghostreborn.akiratv.ui.EpisodesActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnimeDetailsFragment : Fragment() {

    private lateinit var animeThumbnail: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.details_layout, container, false)
        animeThumbnail = view.findViewById(R.id.anime_thumbnail)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            val animeDetails = AllAnimeParser().animeDetails(MainFragment.allAnimeID)
            withContext(Dispatchers.Main) {
                view.findViewById<TextView>(R.id.anime_name).text = animeDetails.name
                animeThumbnail.load(animeDetails.thumbnail)
                view.findViewById<CardView>(R.id.watch_button).setOnClickListener {
                    startActivity(Intent(requireContext(), EpisodesActivity::class.java))
                }
                if (animeDetails.prequel.isNotEmpty()) {
                    view.findViewById<CardView>(R.id.prequel_button).apply {
                        visibility = View.VISIBLE
                        setOnClickListener {
                            MainFragment.allAnimeID = animeDetails.prequel
                            startActivity(
                                Intent(
                                    requireContext(),
                                    AnimeDetailsActivity::class.java
                                )
                            )
                        }
                    }
                }
                if (animeDetails.sequel.isNotEmpty()) {
                    view.findViewById<CardView>(R.id.sequel_button).apply {
                        visibility = View.VISIBLE
                        setOnClickListener {
                            MainFragment.allAnimeID = animeDetails.sequel
                            startActivity(
                                Intent(
                                    requireContext(),
                                    AnimeDetailsActivity::class.java
                                )
                            )
                        }
                    }
                }
            }
        }
    }

}