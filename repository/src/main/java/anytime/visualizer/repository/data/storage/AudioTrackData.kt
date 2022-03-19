package anytime.visualizer.repository.data.storage

import android.net.Uri
import anytime.visualizer.repository.entity.storage.AudioTrackEntity

data class AudioTrackData (
    val id: Long,
    val displayName: String,
    val contentUri: Uri,
    val isMusic: Int
)

fun AudioTrackData.toEntity() = AudioTrackEntity(
    id = id,
    title = displayName,
    contentUri = contentUri
)

fun List<AudioTrackData>.toEntity() = map {
    it.toEntity()
}
