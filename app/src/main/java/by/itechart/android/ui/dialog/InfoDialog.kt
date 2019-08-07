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
        with(acceptButton) {
            setOnClickListener { acceptClickListener?.invoke() }
            uiModel.acceptText?.let { text = uiModel.acceptText }
        }
        titleTextView.text = uiModel.title
        messageTextView.text = uiModel.message
        uiModel.icon?.let { awardImageView.load(it) }
    }

}