package anytime.visualizer.repository.data.storage

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import anytime.visualizer.repository.AUDIO_QUEUE_TABLE_NAME
import anytime.visualizer.repository.entity.storage.AudioQueueEntity

@Entity(tableName = AUDIO_QUEUE_TABLE_NAME)
data class AudioQueueData (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "content_uri") val contentUri: String
)

fun AudioQueueData.toEntity() = AudioQueueEntity(
    id = id,
    title = title,
    contentUri = Uri.parse(contentUri)
)

fun List<AudioQueueData>.toEntity() = map {
    it.toEntity()
}