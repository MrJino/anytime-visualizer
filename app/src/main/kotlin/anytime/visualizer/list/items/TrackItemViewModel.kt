package anytime.visualizer.list.items

import androidx.lifecycle.MutableLiveData
import anytime.visualizer.list.BaseItemViewModel
import anytime.visualizer.model.AudioTrackModel

class TrackItemViewModel(layoutId: Int, model: AudioTrackModel) : BaseItemViewModel(layoutId, model) {
    val title = MutableLiveData(model.title)
}