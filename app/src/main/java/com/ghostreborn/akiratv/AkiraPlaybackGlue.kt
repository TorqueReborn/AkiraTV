package com.ghostreborn.akiratv

import android.content.Context
import android.view.KeyEvent
import android.view.View
import androidx.leanback.media.PlaybackTransportControlGlue
import androidx.leanback.media.PlayerAdapter

class AkiraPlaybackGlue (context: Context, playerAdapter: PlayerAdapter) :
    PlaybackTransportControlGlue<PlayerAdapter>(context, playerAdapter) {

    override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            seekTo(currentPosition + 10000)
            return true
        }else if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            seekTo(currentPosition - 10000)
            return true
        }else if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
            if (isPlaying) pause() else play()
            return true
        }
        return super.onKey(v, keyCode, event)
    }
}
