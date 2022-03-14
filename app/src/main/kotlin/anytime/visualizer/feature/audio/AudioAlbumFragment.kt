package anytime.visualizer.feature.audio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import anytime.visualizer.common.AVDebugLog
import dagger.hilt.android.AndroidEntryPoint
import noh.jinil.app.anytime.R
import noh.jinil.app.anytime.databinding.FragmentAudioAlbumBinding

@AndroidEntryPoint
class AudioAlbumFragment : Fragment() {

    private val logTag = AudioAlbumFragment::class.simpleName
    private val albumViewModel: AudioAlbumViewModel by activityViewModels()
    private lateinit var binding: FragmentAudioAlbumBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AVDebugLog.i(logTag, "onCreateView-()")
        AudioActivity.actionBarTitle.postValue(getString(R.string.action_bar_album))

        binding = DataBindingUtil.inflate<FragmentAudioAlbumBinding?>(inflater, R.layout.fragment_audio_album, container, false).apply {
            viewModel = albumViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }
}