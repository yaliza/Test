package by.itechart.android.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import by.itechart.android.R
import by.itechart.android.ext.dpToPx


class LoginButton @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : CardView(context, attrs, defStyleAttr) {

    private val textView = TextView(context)
    private val imageView = ImageView(context)
    private val imageSize = 32.dpToPx()

    init {
        initAttributes(attrs)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        val textLP = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply { gravity = Gravity.CENTER }
        addView(textView, textLP)

        val imageLP = LayoutParams(imageSize, imageSize).apply {
            gravity = Gravity.CENTER_VERTICAL
            marginStart = 24.dpToPx()
        }
        addView(imageView, imageLP)
    }

    private fun initAttributes(attrs: AttributeSet?) =
            context.theme.obtainStyledAttributes(attrs, R.styleable.LoginButton, 0, 0).apply {
                if (hasValue(R.styleable.LoginButton_lb_text)) {
                    textView.text = getString(R.styleable.LoginButton_lb_text)
                }
                if (hasValue(R.styleable.LoginButton_lb_image)) {
                    imageView.setImageDrawable(getDrawable(R.styleable.LoginButton_lb_image))
                }
                recycle()
            }

}