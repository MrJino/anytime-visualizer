package noh.jinil.app.kotlin.player

import android.net.Uri

interface PlayerApi {
    fun play(contentUri: Uri)
    fun pause()
    fun stop()
}