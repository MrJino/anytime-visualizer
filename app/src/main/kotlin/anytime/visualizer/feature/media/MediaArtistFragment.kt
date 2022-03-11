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
import noh.jinil.app.anytime.databinding.FragmentMediaArtistBinding
import noh.jinil.app.anytime.databinding.FragmentMediaTrackBinding

@AndroidEntryPoint
class MediaArtistFragment : Fragment() {

    private val logTag = MediaArtistFragment::class.simpleName
    private val artistViewModel: MediaArtistViewModel by activityViewModels()
    private lateinit var binding: FragmentMediaArtistBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AVDebugLog.i(logTag, "onCreateView-()")
        binding = DataBindingUtil.inflate<FragmentMediaArtistBinding?>(inflater, R.layout.fragment_media_artist, container, false).apply {
            viewModel = artistViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }
}