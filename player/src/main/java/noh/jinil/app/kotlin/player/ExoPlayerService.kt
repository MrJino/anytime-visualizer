package noh.jinil.app.kotlin.player

import android.content.Context
import android.net.Uri
import anytime.visualizer.common.AVDebugLog
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player

class ExoPlayerService(context: Context) : PlayerApi {

    private val logTag = ExoPlayerService::class.simpleName
    private val exoPlayer = ExoPlayer.Builder(context).build()

    private var stateChangedListener: ((PlayerApi.State) -> Unit)? = null
    private var state =  PlayerApi.State.STOPPED

    init {
        exoPlayer.addListener(object: Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                AVDebugLog.l(logTag, "onPlaybackStateChanged: $playbackState")
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                state = if (isPlaying) {
                    PlayerApi.State.PLAYING
                } else {
                    PlayerApi.State.PAUSED
                }
                stateChangedListener?.invoke(state)
            }
        })
    }

    override fun setStateChangedListener(listener: (PlayerApi.State) -> Unit) {
        stateChangedListener = listener
    }

    override fun play(contentUri: Uri) {
        AVDebugLog.w(logTag, "play-() $contentUri")
        val mediaItem = MediaItem.fromUri(contentUri)
        with(exoPlayer) {
            setMediaItem(mediaItem)
            prepare()
            play()
        }
    }

    override fun resume() {
        AVDebugLog.w(logTag, "resume-()")
        exoPlayer.play()
    }

    override fun pause() {
        AVDebugLog.w(logTag, "pause-()")
        exoPlayer.pause()
    }

    override fun stop() {
        AVDebugLog.w(logTag, "stop-()")
        exoPlayer.stop()
    }

    override fun prev() {
        AVDebugLog.w(logTag, "prev-()")
        exoPlayer.seekToPrevious()
    }

    override fun next() {
        AVDebugLog.w(logTag, "next-()")
        exoPlayer.seekToNext()
    }

    override fun isPlaying(): Boolean {
        return exoPlayer.isPlaying
    }

    override fun duration(): Long {
        return exoPlayer.duration
    }

    override fun playtime(): Long {
        return exoPlayer.currentPosition
    }

    override fun state(): PlayerApi.State {
        return state
    }
}