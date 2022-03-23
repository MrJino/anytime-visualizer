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
import noh.jinil.app.anytime.databinding.FragmentAudioFolderBinding

@AndroidEntryPoint
class AudioFolderFragment : Fragment() {

    private val logTag = AudioFolderFragment::class.simpleName
    private val folderViewModel: AudioFolderViewModel by activityViewModels()
    private lateinit var binding: FragmentAudioFolderBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AVDebugLog.i(logTag, "onCreateView-()")
        AudioLibraryActivity.actionBarTitle.postValue(getString(R.string.action_bar_folder))

        binding = DataBindingUtil.inflate<FragmentAudioFolderBinding?>(inflater, R.layout.fragment_audio_folder, container, false).apply {
            viewModel = folderViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }
}