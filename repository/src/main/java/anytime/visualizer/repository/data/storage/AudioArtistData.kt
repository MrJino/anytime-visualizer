package anytime.visualizer.repository.data.storage

import anytime.visualizer.repository.entity.storage.AudioArtistEntity

data class AudioArtistData (
    val id: Long,
    val artist: String,
)

fun AudioArtistData.toEntity() = AudioArtistEntity(
    id = id,
    name = artist
)

fun List<AudioArtistData>.toEntity() = map {
    it.toEntity()
}
