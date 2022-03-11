package anytime.visualizer.manager

import android.content.Context
import android.content.Intent
import anytime.visualizer.feature.media.MediaActivity

object MoveManager {

    @JvmStatic
    fun gotoMusicLibrary(context: Context) {
        val intent = Intent(context, MediaActivity::class.java)
        context.startActivity(intent)
    }
}