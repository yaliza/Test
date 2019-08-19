package by.itechart.android.ui.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlin.math.roundToInt


@Parcelize
data class QuizResultUIModel(
    var correct: Int,
    var questions: Int,
    var levelId: String
) : Parcelable {

    val rating: Int
        get() = (3 * correct.toFloat() / questions).roundToInt()

}