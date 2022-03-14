package anytime.visualizer.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.RecyclerView
import noh.jinil.app.anytime.BR

class AppListAdapter : RecyclerView.Adapter<AppListAdapter.ViewHolder>() {

    private val itemList = ArrayList<BaseItemViewModel>()
    var itemShowAnimation: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return ViewHolder(view).apply {
            binding?.lifecycleOwner = parent.findFragment<Fragment>().viewLifecycleOwner
        }
    }

    override fun getItemViewType(position: Int): Int {
        return itemList[position].layoutId
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun getItemId(position: Int): Long {
        return itemList[position].itemId
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.setVariable(BR.viewModel, itemList[position])
        if (itemShowAnimation) {
            setFadeAnimation(holder.binding?.root)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding: ViewDataBinding? = DataBindingUtil.bind(view)
    }

    private fun setFadeAnimation(view: View?) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 200
        anim.interpolator = AccelerateInterpolator(2.0f)
        view?.startAnimation(anim)
    }

    fun setData(data: BaseItemViewModel, index: Int) {
        if (index >= itemList.size) {
            return
        }
        itemList[index] = data
        notifyItemChanged(index)
    }

    fun addData(data: BaseItemViewModel, index: Int? = null): Int {
        if (index == null) {
            itemList.add(data)
        } else {
            itemList.add(index, data)
        }

        val updateIdx = index ?: itemCount - 1
        notifyItemInserted(updateIdx)
        return updateIdx
    }

    fun addList(list: List<BaseItemViewModel>, index: Int? = null) {
        if (index == null) {
            itemList.addAll(list)
        } else {
            itemList.addAll(index, list)
        }

        val updateIdx = index ?: itemCount - 1
        notifyItemRangeInserted(updateIdx, list.size)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(dataList: List<BaseItemViewModel>) {
        itemList.clear()
        itemList.addAll(dataList)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearData() {
        itemList.clear()
        notifyDataSetChanged()
    }

    fun getData(index: Int): BaseItemViewModel? {
        return if (index >= 0 && index < itemList.size) {
            itemList[index]
        } else {
            null
        }
    }

    fun getList(): List<BaseItemViewModel> = itemList

    inline fun <reified T: BaseItemViewModel> getItems() =
        getList().filterIsInstance<T>()

    inline fun <reified T: BaseItemViewModel> getCheckedItems() =
        getList().filterIsInstance<T>().filter { it.checked.get() }

    inline fun <reified T: BaseItemViewModel> getCheckedCount() =
        getItems<T>().filter { it.checked.get() }.count()
}