package noh.jinil.app.kotlin.player

import android.content.Context
import android.net.Uri
import anytime.visualizer.common.AVDebugLog
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class ExoPlayerService(context: Context) : PlayerApi {

    private val logTag = ExoPlayerService::class.simpleName
    private val exoPlayer = ExoPlayer.Builder(context).build()

    override fun play(contentUri: Uri) {
        AVDebugLog.w(logTag, "play-() $contentUri")
        val mediaItem = MediaItem.fromUri(contentUri)
        with(exoPlayer) {
            setMediaItem(mediaItem)
            prepare()
            play()
        }
    }

    override fun pause() {
        exoPlayer.pause()
    }

    override fun stop() {
        exoPlayer.stop()
    }
}