package anytime.visualizer.feature.media

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.setupWithNavController
import anytime.visualizer.common.AVDebugLog
import anytime.visualizer.findNavControllerFromFragmentManager
import dagger.hilt.android.AndroidEntryPoint
import noh.jinil.app.anytime.R
import noh.jinil.app.anytime.databinding.ActivityMediaBinding

@AndroidEntryPoint
class MediaActivity : AppCompatActivity() {
    private val logTag = MediaActivity::class.simpleName
    private lateinit var binding: ActivityMediaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AVDebugLog.i(logTag, "onCreate-()")

        binding = ActivityMediaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavView = binding.mediaBottomNav
        val navController = findNavControllerFromFragmentManager(R.id.media_nav_host)

        bottomNavView.setupWithNavController(navController)
    }
}