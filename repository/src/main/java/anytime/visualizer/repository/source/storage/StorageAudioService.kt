package anytime.visualizer.repository.source.storage

import android.content.ContentResolver
import android.content.ContentUris
import android.provider.MediaStore
import anytime.visualizer.common.AVDebugLog
import anytime.visualizer.repository.data.storage.AudioAlbumData
import anytime.visualizer.repository.data.storage.AudioArtistData
import anytime.visualizer.repository.data.storage.AudioFolderData
import anytime.visualizer.repository.data.storage.AudioTrackData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

class StorageAudioService @Inject constructor(
    private val contentResolver: ContentResolver
) {
    private val logTag = StorageAudioService::class.simpleName

    suspend fun queryAudioTracks(): List<AudioTrackData> {
        AVDebugLog.d(logTag, "queryAudioTracks-()")
        val tracks = mutableListOf<AudioTrackData>()

        withContext(Dispatchers.IO) {
            val projection = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.IS_MUSIC
            )

            val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"
            val sortOrder = "${MediaStore.Audio.Media.DATE_ADDED} DESC"

            contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                sortOrder
            )?.use { cursor ->
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
                val displayNameColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)
                val isMusicColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.IS_MUSIC)

                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idColumn)
                    val displayName = cursor.getString(displayNameColumn)
                    val isMusic = cursor.getInt(isMusicColumn)

                    val contentUri = ContentUris.withAppendedId(
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        id
                    )

                    tracks += AudioTrackData(id, displayName, contentUri, isMusic).also {
                        AVDebugLog.v(it.toString())
                    }
                }
            }
        }
        return tracks
    }

    suspend fun queryAudioAlbum(): List<AudioAlbumData> {
        AVDebugLog.w(logTag, "queryAudioAlbum-()")
        val albums = mutableListOf<AudioAlbumData>()

        withContext(Dispatchers.IO) {
            val projection = arrayOf(
                MediaStore.Audio.Albums._ID,
                MediaStore.Audio.Albums.ALBUM,
                MediaStore.Audio.Albums.ARTIST,
            )

            contentResolver.query(
                MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                null
            )?.use { cursor ->
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums._ID)
                val albumColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM)
                val artistColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ARTIST)

                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idColumn)
                    val album = cursor.getString(albumColumn)
                    val artist = cursor.getString(artistColumn)

                    albums += AudioAlbumData(id, album, artist).also {
                        AVDebugLog.v(it.toString())
                    }
                }
            }
        }
        return albums
    }

    suspend fun queryAudioArtist(): List<AudioArtistData> {
        AVDebugLog.w(logTag, "queryAudioArtist-()")
        val artists = mutableListOf<AudioArtistData>()

        withContext(Dispatchers.IO) {
            val projection = arrayOf(
                MediaStore.Audio.Artists._ID,
                MediaStore.Audio.Artists.ARTIST
            )

            contentResolver.query(
                MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                null
            )?.use { cursor ->
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Artists._ID)
                val artistColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Artists.ARTIST)

                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idColumn)
                    val artist = cursor.getString(artistColumn)

                    artists += AudioArtistData(id, artist).also {
                        AVDebugLog.v(it.toString())
                    }
                }
            }
        }
        return artists
    }

    suspend fun queryAudioFolder(): List<AudioFolderData> {
        AVDebugLog.w(logTag, "queryAudioFolder-()")
        val folders = mutableListOf<AudioFolderData>()

        withContext(Dispatchers.IO) {
            val projection = arrayOf(
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.IS_MUSIC
            )

            val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"

            contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                null
            )?.use { cursor ->
                val dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)

                val folderMap = mutableMapOf<String, String>()
                while (cursor.moveToNext()) {
                    val data = cursor.getString(dataColumn)
                    File(data).parentFile?.let { parent ->
                        folderMap.put(parent.path, parent.name)
                    }
                }
                folderMap.forEach { map ->
                    folders += AudioFolderData(path = map.key, name = map.value).also {
                        AVDebugLog.v(it.toString())
                    }
                }
            }
        }
        return folders
    }
}