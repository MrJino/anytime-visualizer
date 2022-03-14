package anytime.visualizer.list.items

import androidx.lifecycle.MutableLiveData
import anytime.visualizer.list.BaseItemViewModel
import anytime.visualizer.model.AudioAlbumModel

class AlbumItemViewModel(layoutId: Int, model: AudioAlbumModel) : BaseItemViewModel(layoutId, model) {
    val title = MutableLiveData(model.title)
}