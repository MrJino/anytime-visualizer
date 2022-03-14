package anytime.visualizer.model

import anytime.visualizer.repository.entity.storage.AudioFolderEntity

data class AudioFolderModel(
    val name: String,
    val path: String
) {
    companion object {
        fun fromEntity(entity: AudioFolderEntity) = AudioFolderModel(
            name = entity.name,
            path = entity.path
        )
    }
}
