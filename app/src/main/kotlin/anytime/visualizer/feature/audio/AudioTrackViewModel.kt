package anytime.visualizer.feature.audio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import anytime.visualizer.list.AppListAdapter
import anytime.visualizer.list.items.TrackItemViewModel
import anytime.visualizer.model.AudioTrackModel
import anytime.visualizer.model.toAudioQueue
import anytime.visualizer.repository.RepositoryApi
import anytime.visualizer.repository.entity.storage.AudioQueueEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import noh.jinil.app.anytime.R
import javax.inject.Inject

@HiltViewModel
class AudioTrackViewModel @Inject constructor(
    private val repository: RepositoryApi
) : ViewModel() {
    init {
        loadAudioTracks()
    }

    var onPlayRequest: ((List<AudioQueueEntity>) -> Unit)? = null
    var onItemChecked: ((Int) -> Unit)? = null

    val adapter = AppListAdapter()

    fun getCheckedItems(): List<AudioQueueEntity> {
        return adapter.getCheckedItems<TrackItemViewModel>().map {
            it.model.toAudioQueue()
        }
    }

    fun clearCheckedItems() {
        adapter.getItems<TrackItemViewModel>().forEach {
            it.checked.postValue(false)
        }
    }

    private fun loadAudioTracks() {
        viewModelScope.launch {
            val tracks = repository.getAudioTracks().map { entity ->
                AudioTrackModel.fromEntity(entity)
            }
            generate(tracks)
        }
    }

    private fun generate(list: List<AudioTrackModel>) {
        adapter.clearData()
        list.forEach { track ->
            adapter.addData(TrackItemViewModel(R.layout.item_audio_track, track).apply {
                onChecked = {
                    onItemChecked?.invoke(adapter.getCheckedCount<TrackItemViewModel>())
                }
            })
        }
    }
}