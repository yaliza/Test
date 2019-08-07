package by.itechart.android.ui.dialog

import android.content.Context
import android.view.Gravity
import by.itechart.android.R
import by.itechart.android.ui.entity.DialogUIModel
import kotlinx.android.synthetic.main.dialog_bottom_info.*


class BottomInfoDialog(
    context: Context,
    uiModel: DialogUIModel
) : Dialog(context, uiModel, R.layout.dialog_bottom_info, R.style.AppTheme_Dialog_FullWidth) {

    override fun onDialogCreated() {
        dialog?.window?.attributes?.gravity = Gravity.BOTTOM
        with(acceptButton) {
            setOnClickListener { acceptClickListener?.invoke() }
            uiModel.acceptText?.let { text = uiModel.acceptText }
        }
        messageTextView.text = uiModel.message
    }

}