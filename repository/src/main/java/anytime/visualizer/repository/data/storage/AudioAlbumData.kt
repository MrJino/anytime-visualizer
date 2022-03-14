package anytime.visualizer.repository.data.storage

import anytime.visualizer.repository.entity.storage.AudioAlbumEntity

data class AudioAlbumData(
    val id: Long,
    val album: String,
    val artist: String,
)

fun AudioAlbumData.toEntity() = AudioAlbumEntity(
    id = id,
    title = album,
    artist = artist
)

fun List<AudioAlbumData>.toEntity() = map {
    it.toEntity()
}
