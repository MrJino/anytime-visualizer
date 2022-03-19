package anytime.visualizer.repository.source.localDB

import anytime.visualizer.common.AVDebugLog
import anytime.visualizer.repository.data.storage.toEntity
import anytime.visualizer.repository.entity.storage.AudioQueueEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val localAudioDatabase: LocalAudioDatabase
) {
    private val logTag = LocalDataSource::class.simpleName

    suspend fun getAudioQueue(): List<AudioQueueEntity> {
        AVDebugLog.w(logTag, "getAudioQueue-()")
        return localAudioDatabase.audioQueueDao().getAll().toEntity()
    }
}