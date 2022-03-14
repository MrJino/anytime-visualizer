package anytime.visualizer.feature.audio

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import anytime.visualizer.common.AVDebugLog
import anytime.visualizer.findNavControllerFromFragmentManager
import dagger.hilt.android.AndroidEntryPoint
import noh.jinil.app.anytime.R
import noh.jinil.app.anytime.databinding.ActivityAudioBinding

@AndroidEntryPoint
class AudioActivity : AppCompatActivity() {
    private val logTag = AudioActivity::class.simpleName
    private lateinit var binding: ActivityAudioBinding

    companion object {
        val actionBarTitle = MutableLiveData("")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AVDebugLog.i(logTag, "onCreate-()")

        binding = ActivityAudioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavView = binding.mediaBottomNav
        val navController = findNavControllerFromFragmentManager(R.id.media_nav_host)

        bottomNavView.setupWithNavController(navController)

        actionBarTitle.observe(this) { title ->
            supportActionBar?.title = title
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home ->
                finish()
        }
        return super.onOptionsItemSelected(item)
    }
}