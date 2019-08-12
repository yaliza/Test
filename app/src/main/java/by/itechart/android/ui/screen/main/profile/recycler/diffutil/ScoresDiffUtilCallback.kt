package by.itechart.android.ui.screen.main.profile.recycler.diffutil

import by.itechart.android.data.entity.Score
import by.itechart.android.ui.base.BaseDiffUtilCallback


class ScoresDiffUtilCallback(
    old: List<Score>,
    new: List<Score>
) : BaseDiffUtilCallback<Score>(old, new) {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        old[oldItemPosition].title == new[newItemPosition].title
}