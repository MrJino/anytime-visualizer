package anytime.visualizer.repository.source.localDB

import androidx.room.Database
import androidx.room.RoomDatabase
import anytime.visualizer.repository.data.storage.AudioQueueData

@Database(
    entities = (arrayOf(AudioQueueData::class)),
    version = 1
)

abstract class LocalAudioDatabase : RoomDatabase() {
    abstract fun audioQueueDao(): LocalAudioQueueDao
}