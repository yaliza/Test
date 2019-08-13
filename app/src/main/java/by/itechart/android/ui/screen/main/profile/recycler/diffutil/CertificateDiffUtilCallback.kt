package by.itechart.android.ui.screen.main.profile.recycler.diffutil

import by.itechart.android.ui.base.BaseDiffUtilCallback
import by.itechart.android.ui.entity.CertificateUIModel


class CertificateDiffUtilCallback(
    old: List<CertificateUIModel>,
    new: List<CertificateUIModel>
) : BaseDiffUtilCallback<CertificateUIModel>(old, new)