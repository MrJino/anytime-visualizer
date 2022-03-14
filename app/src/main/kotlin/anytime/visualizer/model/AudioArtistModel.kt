package anytime.visualizer.model

import anytime.visualizer.repository.entity.storage.AudioArtistEntity

data class AudioArtistModel(
    val id: Long,
    val name: String
) {
    companion object {
        fun fromEntity(entity: AudioArtistEntity) = AudioArtistModel(
            id = entity.id,
            name = entity.name
        )
    }
}
