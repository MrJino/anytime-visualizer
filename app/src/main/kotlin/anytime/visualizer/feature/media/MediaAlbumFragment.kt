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
import noh.jinil.app.anytime.databinding.FragmentMediaAlbumBinding
import noh.jinil.app.anytime.databinding.FragmentMediaTrackBinding

@AndroidEntryPoint
class MediaAlbumFragment : Fragment() {

    private val logTag = MediaAlbumFragment::class.simpleName
    private val albumViewModel: MediaAlbumViewModel by activityViewModels()
    private lateinit var binding: FragmentMediaAlbumBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AVDebugLog.i(logTag, "onCreateView-()")
        binding = DataBindingUtil.inflate<FragmentMediaAlbumBinding?>(inflater, R.layout.fragment_media_album, container, false).apply {
            viewModel = albumViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }
}