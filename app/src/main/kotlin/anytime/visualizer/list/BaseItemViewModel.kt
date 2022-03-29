package anytime.visualizer.list

import androidx.lifecycle.MutableLiveData

abstract class BaseItemViewModel(val layoutId: Int, val data: Any? = null) {
    var itemId: Long = 0
    val checked = MutableLiveData(false)

    var onChecked: ((Boolean?) -> Unit)? = null

    fun toggleChecked() {
        checked.value = checked.value != true
        onChecked?.invoke(checked.value)
    }
}