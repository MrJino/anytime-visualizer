package anytime.visualizer.repository

import anytime.visualizer.repository.entity.storage.AudioAlbumEntity
import anytime.visualizer.repository.entity.storage.AudioArtistEntity
import anytime.visualizer.repository.entity.storage.AudioFolderEntity
import anytime.visualizer.repository.entity.storage.AudioTrackEntity

interface RepositoryApi {
    suspend fun getAudioTracks(): List<AudioTrackEntity>
    suspend fun getAudioAlbums(): List<AudioAlbumEntity>
    suspend fun getAudioArtists(): List<AudioArtistEntity>
    suspend fun getAudioFolders(): List<AudioFolderEntity>
}