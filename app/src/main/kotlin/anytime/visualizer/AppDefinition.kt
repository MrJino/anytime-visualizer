package anytime.visualizer

import androidx.lifecycle.MutableLiveData

enum class AppBottomMenu {
    NONE,
    PLAY
}

val appActionBarTitle = MutableLiveData("")
val appBottomMenu = MutableLiveData(AppBottomMenu.NONE)