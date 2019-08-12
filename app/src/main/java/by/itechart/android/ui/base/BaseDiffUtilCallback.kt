package by.itechart.android.ui.base

import androidx.recyclerview.widget.DiffUtil


open class BaseDiffUtilCallback<T>(
    protected val old: List<T>,
    protected val new: List<T>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        old[oldItemPosition] == new[newItemPosition]

    override fun getOldListSize() = old.size

    override fun getNewListSize() = new.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        old[oldItemPosition] == new[newItemPosition]

}