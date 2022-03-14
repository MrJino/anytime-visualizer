package anytime.visualizer.feature.audio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import anytime.visualizer.list.AppListAdapter
import anytime.visualizer.list.items.ArtistItemViewModel
import anytime.visualizer.model.AudioArtistModel
import anytime.visualizer.repository.RepositoryApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import noh.jinil.app.anytime.R
import javax.inject.Inject

@HiltViewModel
class AudioArtistViewModel @Inject constructor(
    private val repository: RepositoryApi
)  : ViewModel() {

    init {
        loadAudioArtists()
    }

    val adapter = AppListAdapter()

    private fun loadAudioArtists() {
        viewModelScope.launch {
            val artists = repository.getAudioArtists().map { entity ->
                AudioArtistModel.fromEntity(entity)
            }
            generate(artists)
        }
    }

    private fun generate(list: List<AudioArtistModel>) {
        adapter.clearData()
        list.forEach { artist ->
            adapter.addData(ArtistItemViewModel(R.layout.item_audio_artist, artist))
        }
    }
}