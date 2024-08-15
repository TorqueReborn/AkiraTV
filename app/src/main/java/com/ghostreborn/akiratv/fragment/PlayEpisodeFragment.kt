package com.ghostreborn.akiratv.fragment

import android.net.Uri
import android.os.Bundle
import androidx.leanback.app.VideoSupportFragment
import androidx.leanback.app.VideoSupportFragmentGlueHost
import androidx.leanback.media.MediaPlayerAdapter
import androidx.leanback.media.PlaybackTransportControlGlue
import androidx.leanback.widget.PlaybackControlsRow

class PlayEpisodeFragment : VideoSupportFragment() {
    private lateinit var mTransportControlGlue: PlaybackTransportControlGlue<MediaPlayerAdapter>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val glueHost = VideoSupportFragmentGlueHost(this@PlayEpisodeFragment)
        val playerAdapter = MediaPlayerAdapter(activity)
        playerAdapter.setRepeatAction(PlaybackControlsRow.RepeatAction.INDEX_NONE)

        mTransportControlGlue = PlaybackTransportControlGlue(activity, playerAdapter)
        mTransportControlGlue.host = glueHost
        mTransportControlGlue.title = "Episode Title"
        mTransportControlGlue.subtitle = "Episode Description"
        mTransportControlGlue.playWhenPrepared()

        playerAdapter.setDataSource(Uri.parse(MainFragment.episodeUrl))

    }

    override fun onPause() {
        super.onPause()
        mTransportControlGlue.pause()
    }
}