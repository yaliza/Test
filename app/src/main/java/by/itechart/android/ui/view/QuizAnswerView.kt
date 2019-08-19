package by.itechart.android.ui.view

import android.content.Context
import android.util.AttributeSet
import by.itechart.android.R
import by.itechart.android.ext.dpToPx
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.view_quiz_answer.view.*


class QuizAnswerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    private var borderSize = 1.dpToPx()

    init {
        inflate(context, R.layout.view_quiz_answer, this)
    }

    var isBordered: Boolean = false
        set(value) {
            field = value
            strokeWidth = if (value) borderSize else 0
            invalidate()
        }

    var title: String? = null
        set(value) {
            value?.let {
                field = value
                optionTitleTextView.text = value
            }
        }

}