package anytime.visualizer.manager

import android.content.Context
import android.content.Intent
import anytime.visualizer.AudioLibraryActivity
import anytime.visualizer.MainPlayerActivity

object MoveManager {

    @JvmStatic
    fun gotoMusicLibrary(context: Context) {
        val intent = Intent(context, AudioLibraryActivity::class.java)
        context.startActivity(intent)
    }

    @JvmStatic
    fun gotoMainPlayer(context: Context) {
        val intent = Intent(context, MainPlayerActivity::class.java)
        context.startActivity(intent)
    }
}