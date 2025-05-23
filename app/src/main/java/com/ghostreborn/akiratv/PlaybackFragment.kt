package com.ghostreborn.akiratv

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.leanback.app.VideoSupportFragment
import androidx.leanback.app.VideoSupportFragmentGlueHost
import androidx.leanback.media.MediaPlayerAdapter
import androidx.core.net.toUri
import androidx.leanback.media.PlaybackTransportControlGlue
import androidx.leanback.widget.PlaybackControlsRow

class PlaybackFragment: VideoSupportFragment() {

    private lateinit var mTransportControlGlue: PlaybackTransportControlGlue<MediaPlayerAdapter>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val glueHost = VideoSupportFragmentGlueHost(this@PlaybackFragment)
        val playerAdapter = MediaPlayerAdapter(activity)
        playerAdapter.setRepeatAction(PlaybackControlsRow.RepeatAction.INDEX_NONE)

        mTransportControlGlue = PlaybackTransportControlGlue(activity, playerAdapter)
        mTransportControlGlue.host = glueHost
        mTransportControlGlue.title = "ONE"
        mTransportControlGlue.subtitle = "DESCRIPTION"
        mTransportControlGlue.playWhenPrepared()

        playerAdapter.setDataSource("https://repackager.wixmp.com/video.wixstatic.com/video/bd1bd7_43978885514d4ed5b048a2a7c84187c5/,480p,720p,1080p,/mp4/file.mp4.urlset/master.m3u8".toUri())

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}