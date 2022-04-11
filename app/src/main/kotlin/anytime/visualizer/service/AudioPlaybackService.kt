package anytime.visualizer.service

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaMetadata
import android.net.Uri
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.core.app.NotificationCompat
import androidx.media.app.NotificationCompat.MediaStyle
import anytime.visualizer.common.AVDebugLog
import anytime.visualizer.repository.entity.storage.AudioQueueEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import noh.jinil.app.anytime.R
import noh.jinil.app.kotlin.player.PlayerApi
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class AudioPlaybackService : Service() {

    private val logTag = AudioPlaybackService::class.simpleName
    private val binder: IBinder = MyBinder()

    @Inject
    lateinit var player: PlayerApi

    private val playQueue = LinkedList<AudioQueueEntity>()
    private var playIndex = 0

    companion object {
        private const val NOTIFICATION_ID = 1
        private const val CHANNEL_ID = "visualizer_channel_id"
        private const val CHANNEL_NAME = "Visualizer Channel"

        private const val ACTION_PAUSE = "playback.service.action.pause"
        private const val ACTION_RESUME = "playback.service.action.resume"
        private const val ACTION_NEXT = "playback.service.action.next"
        private const val ACTION_PREV = "playback.service.action.prev"
    }

    override fun onCreate() {
        AVDebugLog.w(logTag, "onCreate-()")
        super.onCreate()

        val actionFilter = IntentFilter().apply {
            addAction(ACTION_PAUSE)
            addAction(ACTION_RESUME)
            addAction(ACTION_PREV)
            addAction(ACTION_NEXT)
        }
        registerReceiver(intentReceiver, actionFilter)

        player.setStateChangedListener(playStateChangedListener)
    }

    override fun onDestroy() {
        AVDebugLog.w(logTag, "onDestroy-()")
        super.onDestroy()
        unregisterReceiver(intentReceiver)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
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
        AVDebugLog.d(logTag, "addQueue-() size: ${list.size}")
        if (list.isEmpty()) {
            return
        }
        playIndex = playQueue.size
        playQueue.addAll(list)
        playAudio()
    }

    fun nextQueue() {
        playIndex++
        if (playIndex >= playQueue.size) {
            playIndex = 0
        }
        playAudio()
    }

    private val intentReceiver = object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                ACTION_PAUSE -> pauseAudio()
                ACTION_RESUME -> resumeAudio()
                ACTION_PREV -> player.prev()
                ACTION_NEXT -> nextQueue()
            }
        }
    }

    private val playStateChangedListener: (PlayerApi.State) -> Unit = { _ ->
        showNotificationPlayer(playQueue[playIndex])
    }

    /**
     * Notification Player
     */
    private fun showNotificationPlayer(track: AudioQueueEntity?) {
        track ?: return

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = getSystemService(NotificationManager::class.java)
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_LOW
            )
            manager.createNotificationChannel(serviceChannel)

            startForegroundService(Intent(applicationContext, AudioPlaybackService::class.java))
        }

        val mediaSession = MediaSessionCompat(this, "PlayerService").apply {
            setMetadata(
                MediaMetadataCompat.Builder()
                    .putString(MediaMetadata.METADATA_KEY_TITLE, track.title)
                    .putLong(MediaMetadata.METADATA_KEY_DURATION, player.duration())
                    .build()
            )
            setPlaybackState(
                PlaybackStateCompat.Builder()
                    .setState(
                        player.state().toPlaybackState(),
                        player.playtime(),
                        1.0f
                    )
                    .setActions(PlaybackStateCompat.ACTION_SEEK_TO)
                    .build()
            )
        }

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setStyle(MediaStyle().setMediaSession(mediaSession.sessionToken))
            .setSmallIcon(R.drawable.statusbar)
            .build()

        val toggleAction = if (player.isPlaying()) {
            Notification.Action.Builder(
                R.drawable.music_button_pause,
                "Pause",
                PendingIntent.getBroadcast(this, 0, Intent(ACTION_PAUSE), 0))
                .build()
        } else {
            Notification.Action.Builder(
                R.drawable.music_button_play,
                "Resume",
                PendingIntent.getBroadcast(this, 0, Intent(ACTION_RESUME), 0))
                .build()
        }

        val prevAction = Notification.Action.Builder(
            R.drawable.music_button_prev,
            "Prev",
            PendingIntent.getBroadcast(this, 0, Intent(ACTION_PREV), 0))
            .build()

        val nextAction = Notification.Action.Builder(
            R.drawable.music_button_next,
            "Next",
            PendingIntent.getBroadcast(this, 0, Intent(ACTION_NEXT), 0))
            .build()

        notification.actions = arrayOf(prevAction, toggleAction, nextAction)

        startForeground(NOTIFICATION_ID, notification)
    }

    private fun playAudio() {
        val track = playQueue[playIndex]
        player.play(track.contentUri)
    }

    private fun pauseAudio() {
        player.pause()
    }

    private fun resumeAudio() {
        player.resume()
    }
}