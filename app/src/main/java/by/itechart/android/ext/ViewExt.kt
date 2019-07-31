package by.itechart.android.ext

import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.setBackgroundColorRes(@ColorRes colorId: Int) {
    setBackgroundColor(ContextCompat.getColor(this.context, colorId))
}