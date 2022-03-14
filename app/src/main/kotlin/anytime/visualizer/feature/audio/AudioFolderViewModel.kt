package anytime.visualizer.feature.audio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import anytime.visualizer.list.AppListAdapter
import anytime.visualizer.list.items.FolderItemViewModel
import anytime.visualizer.model.AudioFolderModel
import anytime.visualizer.repository.RepositoryApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import noh.jinil.app.anytime.R
import javax.inject.Inject

@HiltViewModel
class AudioFolderViewModel @Inject constructor(
    private val repository: RepositoryApi
) : ViewModel() {

    init {
        loadAudioFolders()
    }

    val adapter = AppListAdapter()

    private fun loadAudioFolders() {
        viewModelScope.launch {
            val folders = repository.getAudioFolders().map { entity ->
                AudioFolderModel.fromEntity(entity)
            }
            generate(folders)
        }
    }

    private fun generate(list: List<AudioFolderModel>) {
        adapter.clearData()
        list.forEach { folder ->
            adapter.addData(FolderItemViewModel(R.layout.item_audio_folder, folder))
        }
    }
}