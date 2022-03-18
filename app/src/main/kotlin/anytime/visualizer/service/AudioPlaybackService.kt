package anytime.visualizer.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class AudioPlaybackService : Service() {
    private val binder: IBinder = MyBinder()

    override fun onBind(inten: Intent?): IBinder {
        return binder
    }

    inner class MyBinder : Binder() {
        fun getService() = this@AudioPlaybackService
    }
}