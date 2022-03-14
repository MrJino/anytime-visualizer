package anytime.visualizer.repository.entity.storage

import android.net.Uri

data class AudioTrackEntity(
    val id: Long,
    val title: String,
    val contentUri: Uri
)