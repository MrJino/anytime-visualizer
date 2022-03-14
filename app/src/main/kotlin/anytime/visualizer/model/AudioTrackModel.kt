package anytime.visualizer.model

import android.net.Uri
import anytime.visualizer.repository.entity.storage.AudioTrackEntity

data class AudioTrackModel(
    val id: Long,
    val title: String,
    val contentUri: Uri
) {
    companion object {
        fun fromEntity(entity: AudioTrackEntity) = AudioTrackModel(
            id = entity.id,
            title = entity.title,
            contentUri = entity.contentUri
        )
    }
}
