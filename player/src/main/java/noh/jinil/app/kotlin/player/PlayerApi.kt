package noh.jinil.app.kotlin.player

import android.net.Uri
import android.support.v4.media.session.PlaybackStateCompat

interface PlayerApi {
    enum class State {
        STOPPED,
        PLAYING,
        PAUSED;

        fun toPlaybackState() = when (this) {
            STOPPED -> PlaybackStateCompat.STATE_NONE
            PLAYING -> PlaybackStateCompat.STATE_PLAYING
            PAUSED -> PlaybackStateCompat.STATE_PAUSED
        }
    }

    fun play(contentUri: Uri)
    fun pause()
    fun resume()
    fun stop()
    fun prev()
    fun next()

    fun setStateChangedListener(listener: (State) -> Unit)
    fun isPlaying(): Boolean
    fun state(): State
    fun duration(): Long
    fun playtime(): Long
}