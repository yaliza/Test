package by.itechart.android.ui.screen.module

import by.itechart.android.data.entity.Topic
import by.itechart.android.ui.base.BaseDiffUtilCallback


class TopicDiffUtilCallback(
    old: List<Topic>,
    new: List<Topic>
) : BaseDiffUtilCallback<Topic>(old, new) {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        old[oldItemPosition].title == new[newItemPosition].title
}