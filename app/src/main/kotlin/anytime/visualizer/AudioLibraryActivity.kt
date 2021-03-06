package anytime.visualizer

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.navigation.ui.setupWithNavController
import anytime.visualizer.common.AVDebugLog
import anytime.visualizer.service.AudioPlaybackService
import dagger.hilt.android.AndroidEntryPoint
import noh.jinil.app.anytime.R
import noh.jinil.app.anytime.databinding.ActivityAudioBinding

@AndroidEntryPoint
class AudioLibraryActivity : AppCompatActivity(), ServiceConnection {
    private val logTag = AudioLibraryActivity::class.simpleName
    private lateinit var binding: ActivityAudioBinding

    var audioService: AudioPlaybackService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AVDebugLog.i(logTag, "onCreate-()")

        binding = ActivityAudioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavView = binding.mediaBottomNav
        val navController = findNavControllerFromFragmentManager(R.id.media_nav_host)

        // Bottom Navigation View 자동 Fragment 이동 처리 등록
        bottomNavView.setupWithNavController(navController)

        // Action Bar 타이틀 세팅
        appActionBarTitle.observe(this) { title ->
            supportActionBar?.title = title
        }

        // Bottom Navigation View 숨김/보기 처리
        appBottomMenu.observe(this) { menu ->
            AVDebugLog.l(logTag, "appBottomMenu: $menu")
            binding.mediaBottomNav.show(menu == AppBottomMenu.NONE)
        }
    }

    override fun onStart() {
        super.onStart()
        bindToAudioService()
    }

    override fun onStop() {
        super.onStop()
        unbindFromAudioService()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home ->
                finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onServiceConnected(p0: ComponentName?, iBinder: IBinder?) {
        audioService = (iBinder as? AudioPlaybackService.MyBinder)?.getService()
    }

    override fun onServiceDisconnected(p0: ComponentName?) {
        audioService = null
    }

    private fun bindToAudioService() {
        val intent = Intent(this, AudioPlaybackService::class.java)
        bindService(intent, this, BIND_AUTO_CREATE)
    }

    private fun unbindFromAudioService() {
        unbindService(this)
    }
}