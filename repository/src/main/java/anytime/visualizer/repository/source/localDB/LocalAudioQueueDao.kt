package anytime.visualizer.repository.source.localDB

import androidx.room.Dao
import androidx.room.Query
import anytime.visualizer.repository.AUDIO_QUEUE_TABLE_NAME
import anytime.visualizer.repository.data.storage.AudioQueueData

@Dao
interface LocalAudioQueueDao {

    @Query("SELECT * FROM $AUDIO_QUEUE_TABLE_NAME")
    suspend fun getAll(): List<AudioQueueData>
}