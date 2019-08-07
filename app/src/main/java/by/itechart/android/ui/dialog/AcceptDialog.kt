package by.itechart.android.ui.dialog

import android.content.Context
import by.itechart.android.R
import by.itechart.android.ui.entity.DialogUIModel
import kotlinx.android.synthetic.main.dialog_accept.*


class AcceptDialog(
    context: Context,
    uiModel: DialogUIModel
) : Dialog(context, uiModel, R.layout.dialog_accept) {

    override fun onDialogCreated() {
        super.onDialogCreated()
        with(declineButton) {
            uiModel.declineText?.let { text = uiModel.declineText }
            setOnClickListener { declineClickListener?.invoke() }
        }
    }

}
