package noh.jinil.app.anytime.utils

import android.content.ContentUris
import android.net.Uri
import android.provider.MediaStore

object MusicUtil {

    @JvmStatic
    fun getSongFileUri(songId: Long): Uri
            = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, songId)
}