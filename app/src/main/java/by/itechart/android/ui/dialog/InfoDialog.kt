package by.itechart.android.ui.dialog

import android.content.Context
import by.itechart.android.R
import by.itechart.android.ext.load
import by.itechart.android.ui.entity.DialogUIModel
import kotlinx.android.synthetic.main.dialog_info.*


class InfoDialog(
    context: Context,
    uiModel: DialogUIModel
) : Dialog(context, uiModel, R.layout.dialog_info) {

    override fun onDialogCreated() {
        super.onDialogCreated()
        titleTextView.text = uiModel.title
        uiModel.icon?.let { awardImageView.load(it) }
    }

}