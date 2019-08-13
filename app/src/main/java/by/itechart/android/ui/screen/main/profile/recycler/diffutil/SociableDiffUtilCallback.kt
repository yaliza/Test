package by.itechart.android.ui.screen.main.profile.recycler.diffutil

import by.itechart.android.ui.base.BaseDiffUtilCallback
import by.itechart.android.ui.entity.SociableUIModel


class SociableDiffUtilCallback(
    old: List<SociableUIModel>,
    new: List<SociableUIModel>
) : BaseDiffUtilCallback<SociableUIModel>(old, new)