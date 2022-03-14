package anytime.visualizer.list.items

import androidx.lifecycle.MutableLiveData
import anytime.visualizer.list.BaseItemViewModel
import anytime.visualizer.model.AudioFolderModel

class FolderItemViewModel(layoutId: Int, model: AudioFolderModel) : BaseItemViewModel(layoutId, model) {
    val name = MutableLiveData(model.name)
    val path = MutableLiveData(model.path)
}