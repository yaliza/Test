package by.itechart.android.ext

import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import by.itechart.android.R

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.setBackgroundColorRes(@ColorRes colorId: Int) {
    setBackgroundColor(ContextCompat.getColor(this.context, colorId))
}

fun View.setBackgroundColor(color: String) {
    when (color) {
        "green" -> setBackgroundColorRes(R.color.green)
        "blue" -> setBackgroundColorRes(R.color.blue)
        else -> setBackgroundColorRes(R.color.grey50)
    }
}