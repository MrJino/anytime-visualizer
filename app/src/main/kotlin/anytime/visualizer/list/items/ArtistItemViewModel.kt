package anytime.visualizer.list.items

import androidx.lifecycle.MutableLiveData
import anytime.visualizer.list.BaseItemViewModel
import anytime.visualizer.model.AudioAlbumModel
import anytime.visualizer.model.AudioArtistModel

class ArtistItemViewModel(layoutId: Int, model: AudioArtistModel) : BaseItemViewModel(layoutId, model) {
    val name = MutableLiveData(model.name)
}