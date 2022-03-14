package anytime.visualizer.list

import androidx.databinding.ObservableBoolean

abstract class BaseItemViewModel(val layoutId: Int, val data: Any? = null) {
    var itemId: Long = 0
    val checked = ObservableBoolean(false)

    var checkCallback: ((Boolean) -> Unit)? = null

    fun toggleChecked() {
        checked.set(!checked.get())
        checkCallback?.invoke(checked.get())
    }
}