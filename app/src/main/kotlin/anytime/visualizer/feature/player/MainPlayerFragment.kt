package anytime.visualizer.feature.player

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
import noh.jinil.app.anytime.databinding.FragmentMainPlayerBinding

@AndroidEntryPoint
class MainPlayerFragment : Fragment()  {
    private val logTag = MainPlayerFragment::class.simpleName
    private val playerViewModel: MainPlayerViewModel by activityViewModels()
    private lateinit var binding: FragmentMainPlayerBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        AVDebugLog.i(logTag, "onCreateView-()")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_player, container, false)
        with (binding) {
            viewModel = playerViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }
}