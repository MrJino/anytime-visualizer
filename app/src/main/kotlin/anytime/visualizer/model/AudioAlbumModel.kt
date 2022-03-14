package anytime.visualizer.model

import anytime.visualizer.repository.entity.storage.AudioAlbumEntity

data class AudioAlbumModel(
    val id: Long,
    val title: String,
    val artist: String
) {
    companion object {
        fun fromEntity(entity: AudioAlbumEntity) = AudioAlbumModel(
            id = entity.id,
            title = entity.title,
            artist = entity.artist
        )
    }
}
