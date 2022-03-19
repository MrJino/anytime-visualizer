package anytime.visualizer.repository

import anytime.visualizer.repository.entity.storage.*

interface RepositoryApi {
    suspend fun getAudioTracks(): List<AudioTrackEntity>
    suspend fun getAudioAlbums(): List<AudioAlbumEntity>
    suspend fun getAudioArtists(): List<AudioArtistEntity>
    suspend fun getAudioFolders(): List<AudioFolderEntity>

    suspend fun getAudioQueue(): List<AudioQueueEntity>
}