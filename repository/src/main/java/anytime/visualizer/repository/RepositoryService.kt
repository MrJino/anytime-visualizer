package anytime.visualizer.repository

import anytime.visualizer.repository.entity.storage.AudioAlbumEntity
import anytime.visualizer.repository.entity.storage.AudioArtistEntity
import anytime.visualizer.repository.entity.storage.AudioFolderEntity
import anytime.visualizer.repository.entity.storage.AudioTrackEntity
import anytime.visualizer.repository.source.storage.StorageDataSource
import javax.inject.Inject

class RepositoryService @Inject constructor (
    private val storageDataSource: StorageDataSource
) : RepositoryApi {
    override suspend fun getAudioTracks(): List<AudioTrackEntity> = storageDataSource.getAudioTracks()
    override suspend fun getAudioAlbums(): List<AudioAlbumEntity> = storageDataSource.getAudioAlbums()
    override suspend fun getAudioArtists(): List<AudioArtistEntity> = storageDataSource.getAudioArtists()
    override suspend fun getAudioFolders(): List<AudioFolderEntity> = storageDataSource.getAudioFolders()
}