package anytime.visualizer

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import anytime.visualizer.common.AVDebugLog
import anytime.visualizer.list.AppListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.checkbox.MaterialCheckBox

object AppBindingAdapter {
    private const val TAG = "AppBindingAdapter"

    @JvmStatic
    @BindingAdapter("adapter")
    fun setAdapter(view: RecyclerView, adapter: AppListAdapter?) {
        AVDebugLog.d(TAG, "setAdapter-() : $adapter")
        view.adapter = adapter
    }

    @JvmStatic
    @BindingAdapter("imageIcon")
    fun setImageIcon(view: ImageView, icon: Int) {
        Glide.with(view.context)
            .load(icon)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("imageDrawable")
    fun setImageDrawable(view: ImageView, drawable: Drawable) {
        Glide.with(view.context)
            .load(drawable)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("imageBitmap")
    fun setImageBitmap(view: ImageView, bitmap: Bitmap?) {
        bitmap ?: return
        view.setImageBitmap(bitmap)
    }

    @JvmStatic
    @BindingAdapter("imageUri", "defaultImg", "isCircle", "radius", requireAll = false)
    fun setImageUri(view: ImageView, uri: Uri?, defaultImg: Drawable?, isCircle: Boolean?, radius: Int?) {
        var myOptions = RequestOptions()
        myOptions = if (isCircle == true) { myOptions.circleCrop() } else { myOptions }
        myOptions = if (radius != null) { myOptions.transform(CenterCrop(), RoundedCorners(radius)) } else { myOptions }

        when (uri) {
            null ->
                view.setImageDrawable(defaultImg)
            else -> {
                Glide.with(view.context)
                    .load(uri)
                    .error(defaultImg)
                    .apply(myOptions)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(view)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("selected")
    fun setSelected(view: View, selected: Boolean) {
        view.isSelected = selected
    }

    @JvmStatic
    @BindingAdapter("checked")
    fun setChecked(view: MaterialCheckBox, checked: Boolean) {
        view.isChecked = checked
    }

    @JvmStatic
    @BindingAdapter("enabled")
    fun setEnabled(view: View, enabled: Boolean) {
        view.isEnabled = enabled
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                view.getChildAt(i).isEnabled = enabled
            }
        }
    }
}