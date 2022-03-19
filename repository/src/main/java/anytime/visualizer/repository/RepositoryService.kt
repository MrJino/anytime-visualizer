package anytime.visualizer.repository

import anytime.visualizer.repository.entity.storage.*
import anytime.visualizer.repository.source.localDB.LocalDataSource
import anytime.visualizer.repository.source.storage.StorageDataSource
import javax.inject.Inject

class RepositoryService @Inject constructor (
    private val storageDataSource: StorageDataSource,
    private val localDataSource: LocalDataSource
) : RepositoryApi {
    override suspend fun getAudioTracks(): List<AudioTrackEntity> = storageDataSource.getAudioTracks()
    override suspend fun getAudioAlbums(): List<AudioAlbumEntity> = storageDataSource.getAudioAlbums()
    override suspend fun getAudioArtists(): List<AudioArtistEntity> = storageDataSource.getAudioArtists()
    override suspend fun getAudioFolders(): List<AudioFolderEntity> = storageDataSource.getAudioFolders()

    override suspend fun getAudioQueue(): List<AudioQueueEntity> = localDataSource.getAudioQueue()
}