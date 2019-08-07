package by.itechart.android.ui.dialog

import android.content.Context
import by.itechart.android.ui.entity.DialogType
import by.itechart.android.ui.entity.DialogUIModel


class DialogBuilder {
    lateinit var context: Context
    lateinit var uiModel: DialogUIModel
    var animation: Dialog.Animation? = null
    var acceptClickListener: (() -> Unit)? = null
    var declineClickListener: (() -> Unit)? = null

    fun build() =
        when (uiModel.dialogType) {
            DialogType.ACCEPT -> AcceptDialog(context, uiModel)
            DialogType.INFO -> InfoDialog(context, uiModel)
            DialogType.BOTTOM -> BottomInfoDialog(context, uiModel)
        }.apply {
            acceptClickListener = this@DialogBuilder.acceptClickListener
            declineClickListener = this@DialogBuilder.declineClickListener
            animation = this@DialogBuilder.animation
        }

}