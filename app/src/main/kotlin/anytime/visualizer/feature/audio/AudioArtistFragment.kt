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
import noh.jinil.app.anytime.databinding.FragmentAudioArtistBinding

@AndroidEntryPoint
class AudioArtistFragment : Fragment() {

    private val logTag = AudioArtistFragment::class.simpleName
    private val artistViewModel: AudioArtistViewModel by activityViewModels()
    private lateinit var binding: FragmentAudioArtistBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AVDebugLog.i(logTag, "onCreateView-()")
        AudioActivity.actionBarTitle.postValue(getString(R.string.action_bar_artist))

        binding = DataBindingUtil.inflate<FragmentAudioArtistBinding?>(inflater, R.layout.fragment_audio_artist, container, false).apply {
            viewModel = artistViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }
}