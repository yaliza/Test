package by.itechart.android.ui.dialog

import android.content.Context
import android.view.Gravity
import by.itechart.android.R
import by.itechart.android.ui.entity.DialogUIModel


class BottomInfoDialog(
    context: Context,
    uiModel: DialogUIModel
) : Dialog(context, uiModel, R.layout.dialog_bottom_info, R.style.AppTheme_Dialog_FullWidth) {

    override fun onDialogCreated() {
        super.onDialogCreated()
        dialog?.window?.attributes?.gravity = Gravity.BOTTOM
    }

}