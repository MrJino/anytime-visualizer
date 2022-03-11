package anytime.visualizer.feature.media

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import anytime.visualizer.common.AVDebugLog
import dagger.hilt.android.AndroidEntryPoint
import noh.jinil.app.anytime.R
import noh.jinil.app.anytime.databinding.FragmentMediaTrackBinding

@AndroidEntryPoint
class MediaTrackFragment : Fragment() {

    private val logTag = MediaTrackFragment::class.simpleName
    private val trackViewModel: MediaTrackViewModel by activityViewModels()
    private lateinit var binding: FragmentMediaTrackBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AVDebugLog.i(logTag, "onCreateView-()")
        binding = DataBindingUtil.inflate<FragmentMediaTrackBinding?>(inflater, R.layout.fragment_media_track, container, false).apply {
            viewModel = trackViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }
}