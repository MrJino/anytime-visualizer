package anytime.visualizer.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import anytime.visualizer.common.AVDebugLog
import anytime.visualizer.repository.entity.storage.AudioQueueEntity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ServiceScoped
import noh.jinil.app.kotlin.player.PlayerApi
import javax.inject.Inject

@AndroidEntryPoint
class AudioPlaybackService : Service() {

    private val logTag = AudioPlaybackService::class.simpleName
    private val binder: IBinder = MyBinder()

    @Inject
    lateinit var player: PlayerApi

    override fun onCreate() {
        AVDebugLog.w(logTag, "onCreate-()")
        super.onCreate()
    }

    override fun onDestroy() {
        AVDebugLog.w(logTag, "onDestroy-()")
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        AVDebugLog.w(logTag, "onStartCommand-()")
        return START_NOT_STICKY
    }

    override fun onBind(inten: Intent?): IBinder {
        AVDebugLog.w(logTag, "onBind-()")
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        AVDebugLog.w(logTag, "onUnbind-()")
        return true
    }

    inner class MyBinder : Binder() {
        fun getService() = this@AudioPlaybackService
    }

    fun addQueue(list: List<AudioQueueEntity>) {
        AVDebugLog.w(logTag, "addQueue-() size: ${list.size}")
        player.play(list[0].contentUri)
    }
}