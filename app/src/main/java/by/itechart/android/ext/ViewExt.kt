package by.itechart.android.ext

import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import by.itechart.android.R
import java.lang.IllegalArgumentException

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.changeBackgroundColor(@ColorRes colorId: Int) {
    setBackgroundColor(ContextCompat.getColor(this.context, colorId))
}

fun View.setBackgroundColor(color: String) {
    when (color) {
        "green" -> changeBackgroundColor(R.color.green)
        "blue" -> changeBackgroundColor(R.color.blue)
        else -> throw IllegalArgumentException("Unknown color")
    }
}