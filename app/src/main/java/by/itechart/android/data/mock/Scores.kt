package by.itechart.android.data.mock

import by.itechart.android.R
import by.itechart.android.ui.entity.ScoreUIModel


object Scores {

    val mock = listOf(
        ScoreUIModel("75% QUIZ", R.drawable.ic_award1),
        ScoreUIModel("90% QUIZ", R.drawable.ic_award2),
        ScoreUIModel("100% QUIZ", R.drawable.ic_award3),
        ScoreUIModel("ALL QUIZZES", R.drawable.ic_award6),
        ScoreUIModel("TEST", R.drawable.ic_award5)
    )

}