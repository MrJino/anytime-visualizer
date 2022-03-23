package anytime.visualizer.feature.audio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import anytime.visualizer.AudioLibraryActivity
import anytime.visualizer.common.AVDebugLog
import dagger.hilt.android.AndroidEntryPoint
import noh.jinil.app.anytime.R
import noh.jinil.app.anytime.databinding.FragmentAudioTrackBinding

@AndroidEntryPoint
class AudioTrackFragment : Fragment() {

    private val logTag = AudioTrackFragment::class.simpleName
    private val trackViewModel: AudioTrackViewModel by activityViewModels()
    private lateinit var binding: FragmentAudioTrackBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AVDebugLog.i(logTag, "onCreateView-()")
        AudioLibraryActivity.actionBarTitle.postValue(getString(R.string.action_bar_track))

        binding = DataBindingUtil.inflate<FragmentAudioTrackBinding?>(inflater, R.layout.fragment_audio_track, container, false).apply {
            viewModel = trackViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        trackViewModel.onPlayRequest = { queue ->
            (requireActivity() as AudioLibraryActivity).audioService?.addQueue(queue)
        }
    }
}