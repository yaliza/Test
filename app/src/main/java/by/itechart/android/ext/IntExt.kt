package by.itechart.android.ext

import android.content.res.Resources
import kotlin.math.roundToInt


fun Int.dpToPx(): Int {
    val density = Resources.getSystem().displayMetrics.density
    return (this * density).roundToInt()
}