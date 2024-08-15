package com.ghostreborn.akiratv.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.ListRow
import com.bumptech.glide.Glide
import com.ghostreborn.akiratv.R
import com.ghostreborn.akiratv.allAnime.AllAnimeParser
import com.ghostreborn.akiratv.presenter.AnimePresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnimeDetailsFragment : Fragment() {

    private lateinit var animeThumbnail: ImageView
    private lateinit var animeBanner: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.details_layout, container, false)
        animeThumbnail = view.findViewById(R.id.anime_thumbnail)
        animeBanner = view.findViewById(R.id.anime_banner)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            val animeDetails = AllAnimeParser().animeDetails(MainFragment.allAnimeID)
            withContext(Dispatchers.Main) {
                Glide.with(requireContext())
                    .load(animeDetails.banner)
                    .into(animeBanner)
                Glide.with(requireContext())
                    .load(animeDetails.thumbnail)
                    .into(animeThumbnail)
            }
        }
    }

}