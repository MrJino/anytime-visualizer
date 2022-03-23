package anytime.visualizer.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import anytime.visualizer.common.AVDebugLog
import anytime.visualizer.repository.entity.storage.AudioQueueEntity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ServiceScoped
import noh.jinil.app.anytime.R
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

    override fun onRebind(intent: Intent?) {
        AVDebugLog.w(logTag, "onRebind-()")
        super.onRebind(intent)
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
        showNotificationPlayer()
    }

    companion object {
        private const val NOTIFICATION_ID = 1
        private const val CHANNEL_ID = "visualizer_channel_id"
        private const val CHANNEL_NAME = "Visualizer Channel"
    }


    private fun showNotificationPlayer() {
        AVDebugLog.d(logTag, "showNotificationPlayer-()")
        val remoteViews = RemoteViews(packageName, R.layout.notification)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = getSystemService(NotificationManager::class.java)
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            manager.createNotificationChannel(serviceChannel)

            startForegroundService(Intent(applicationContext, AudioPlaybackService::class.java))
        }

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContent(remoteViews)
            .setSmallIcon(R.drawable.statusbar)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        startForeground(NOTIFICATION_ID, notification)
    }
}