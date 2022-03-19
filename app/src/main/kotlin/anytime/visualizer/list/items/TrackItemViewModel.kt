package anytime.visualizer.list.items

import androidx.lifecycle.MutableLiveData
import anytime.visualizer.list.BaseItemViewModel
import anytime.visualizer.model.AudioTrackModel

class TrackItemViewModel(layoutId: Int, val model: AudioTrackModel) : BaseItemViewModel(layoutId, model) {
    val title = MutableLiveData(model.title)

    var onClick: ((AudioTrackModel) -> Unit)? = null

    fun performClick() {
        onClick?.invoke(model)
    }
}