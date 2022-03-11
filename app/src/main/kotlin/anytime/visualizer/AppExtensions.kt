package anytime.visualizer

import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

fun AppCompatActivity.findNavControllerFromFragmentManager(@IdRes id: Int) =
    (supportFragmentManager.findFragmentById(id) as NavHostFragment).findNavController()

fun View.show(set: Boolean) = run { if (set) { show() } else { gone() } }
fun View.show() = run { visibility = View.VISIBLE }
fun View.hide() = run { visibility = View.INVISIBLE }
fun View.gone() = run { visibility = View.GONE }

fun BottomNavigationView.setTouchDisable(set: Boolean) {
    menu.forEach { it.isEnabled = !set }
}