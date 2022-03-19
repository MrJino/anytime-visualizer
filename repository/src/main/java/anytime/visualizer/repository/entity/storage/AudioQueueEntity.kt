package anytime.visualizer.repository.entity.storage

import android.net.Uri

data class AudioQueueEntity(
    val id: Long = 0,
    val title: String,
    val contentUri: Uri
)