package by.itechart.android.ui.screen.main.learning.recycler

import by.itechart.android.ui.base.BaseDiffUtilCallback
import by.itechart.android.ui.entity.LevelSectionUIModel
import by.itechart.android.ui.entity.LevelUIModel


class LevelDiffUtilCallback(
    old: List<LevelUIModel>,
    new: List<LevelUIModel>
) : BaseDiffUtilCallback<LevelUIModel>(old, new) {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        if (old[oldItemPosition].viewType == new[newItemPosition].viewType) {
            when (val item = old[oldItemPosition]) {
                is LevelSectionUIModel -> item.title == (new[newItemPosition] as LevelSectionUIModel).title
                else -> true
            }
        } else false

}