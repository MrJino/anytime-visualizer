package anytime.visualizer.repository.data.storage

import anytime.visualizer.repository.entity.storage.AudioFolderEntity
import java.io.File

data class AudioFolderData(
    val name: String,
    val path: String
)

fun AudioFolderData.toEntity() = AudioFolderEntity(
    name = name,
    path = path
)

fun List<AudioFolderData>.toEntity() = map {
    it.toEntity()
}