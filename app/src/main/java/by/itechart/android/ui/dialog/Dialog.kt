package by.itechart.android.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import by.itechart.android.R
import by.itechart.android.ui.entity.DialogUIModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.dialog_bottom_info.acceptButton
import kotlinx.android.synthetic.main.dialog_info.*


abstract class Dialog(
    private val context: Context,
    protected val uiModel: DialogUIModel,
    @LayoutRes layoutID: Int,
    @StyleRes private val styleID: Int = R.style.AppTheme_Dialog
) : LayoutContainer {

    override val containerView: View? = LayoutInflater.from(context).inflate(layoutID, null)
    protected var dialog: AlertDialog? = null
    var acceptClickListener: (() -> Unit)? = null
    var declineClickListener: (() -> Unit)? = null
    var dismissListener: (() -> Unit)? = null
    var animation: Animation? = null

    fun show() {
        if (dialog == null) {
            dialog = createDialog()
            onDialogCreated()
        }
        dialog?.show()
    }

    fun dismiss() {
        dialog?.dismiss()
    }

    private fun createDialog() =
        AlertDialog.Builder(context, styleID)
            .setView(containerView)
            .setOnDismissListener { dismissListener?.invoke() }
            .create()

    open fun onDialogCreated() {
        dialog?.apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window?.attributes?.windowAnimations =
                when (animation) {
                    Animation.BOTTOM -> R.style.AppTheme_Dialog_SlideUpAnimation
                    Animation.RIGHT -> R.style.AppTheme_Dialog_SlideRightAnimation
                    else -> window?.attributes?.windowAnimations
                }
        }

        with(acceptButton) {
            uiModel.acceptText?.let { text = uiModel.acceptText }
            setOnClickListener { acceptClickListener?.invoke() }
        }
        messageTextView.text = uiModel.message
    }

    enum class Animation {
        BOTTOM, RIGHT
    }

}