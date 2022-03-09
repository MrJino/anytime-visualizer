package anytime.visualizer

import android.app.Application
import anytime.visualizer.common.AVDebugLog
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class VisualizerApp : Application() {
    private val logTag = VisualizerApp::class.simpleName

    override fun onCreate() {
        super.onCreate()
        AVDebugLog.i(logTag, "onCreate-()")
    }
}