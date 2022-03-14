package anytime.visualizer.repository.source.storage

import anytime.visualizer.repository.data.storage.toEntity
import anytime.visualizer.repository.entity.storage.AudioAlbumEntity
import anytime.visualizer.repository.entity.storage.AudioArtistEntity
import anytime.visualizer.repository.entity.storage.AudioFolderEntity
import anytime.visualizer.repository.entity.storage.AudioTrackEntity
import javax.inject.Inject

class StorageDataSource @Inject constructor(
    private val storageAudioService: StorageAudioService
) {
    suspend fun getAudioTracks(): List<AudioTrackEntity> = storageAudioService.queryAudioTracks().toEntity()
    suspend fun getAudioAlbums(): List<AudioAlbumEntity> = storageAudioService.queryAudioAlbum().toEntity()
    suspend fun getAudioArtists(): List<AudioArtistEntity> = storageAudioService.queryAudioArtist().toEntity()
    suspend fun getAudioFolders(): List<AudioFolderEntity> = storageAudioService.queryAudioFolder().toEntity()
}