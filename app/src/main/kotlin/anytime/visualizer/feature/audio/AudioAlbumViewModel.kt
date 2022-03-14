package anytime.visualizer.feature.audio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import anytime.visualizer.list.AppListAdapter
import anytime.visualizer.list.items.AlbumItemViewModel
import anytime.visualizer.list.items.TrackItemViewModel
import anytime.visualizer.model.AudioAlbumModel
import anytime.visualizer.model.AudioTrackModel
import anytime.visualizer.repository.RepositoryApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import noh.jinil.app.anytime.R
import javax.inject.Inject

@HiltViewModel
class AudioAlbumViewModel @Inject constructor(
    private val repository: RepositoryApi
): ViewModel() {
    init {
        loadAudioAlbums()
    }

    val adapter = AppListAdapter()

    private fun loadAudioAlbums() {
        viewModelScope.launch {
            val albums = repository.getAudioAlbums().map { entity ->
                AudioAlbumModel.fromEntity(entity)
            }
            generate(albums)
        }
    }

    private fun generate(list: List<AudioAlbumModel>) {
        adapter.clearData()
        list.forEach { album ->
            adapter.addData(AlbumItemViewModel(R.layout.item_audio_album, album))
        }
    }
}