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
import noh.jinil.app.anytime.databinding.FragmentMediaFolderBinding
import noh.jinil.app.anytime.databinding.FragmentMediaTrackBinding

@AndroidEntryPoint
class MediaFolderFragment : Fragment() {

    private val logTag = MediaFolderFragment::class.simpleName
    private val folderViewModel: MediaFolderViewModel by activityViewModels()
    private lateinit var binding: FragmentMediaFolderBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AVDebugLog.i(logTag, "onCreateView-()")
        binding = DataBindingUtil.inflate<FragmentMediaFolderBinding?>(inflater, R.layout.fragment_media_folder, container, false).apply {
            viewModel = folderViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }
}