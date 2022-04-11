package anytime.visualizer

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import anytime.visualizer.common.AVDebugLog
import anytime.visualizer.manager.MoveManager
import anytime.visualizer.service.AudioPlaybackService
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import noh.jinil.app.anytime.R
import noh.jinil.app.anytime.databinding.ActivityMainPlayerBinding

@AndroidEntryPoint
class MainPlayerActivity : AppCompatActivity(), ServiceConnection {
    private lateinit var binding: ActivityMainPlayerBinding
    private val logTag = MainPlayerActivity::class.simpleName

    var audioService: AudioPlaybackService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AVDebugLog.i(logTag, "onCreate-()")

        binding = ActivityMainPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        // Action Bar 메뉴 등록
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)
        }

        // Drawer Navigation View 리스너 등록
        binding.drawerNavView.setNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    override fun onStart() {
        super.onStart()
        bindToAudioService()
    }

    override fun onStop() {
        super.onStop()
        unbindFromAudioService()
    }

    private val onNavigationItemSelectedListener = NavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.drawer_settings -> {

            }
            R.id.drawer_information -> {

            }
        }
        true
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home ->
                binding.drawerLayout.openDrawer(GravityCompat.START)

            R.id.menu_audio_library ->
                MoveManager.gotoMusicLibrary(this);
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