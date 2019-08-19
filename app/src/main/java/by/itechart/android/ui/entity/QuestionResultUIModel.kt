package by.itechart.android.ui.entity


data class QuestionResultUIModel(
    val correctAnswerIndex: Int,
    val answerComment: String,
    val selectedAnswerIndex: Int
) {

    val isAnswerCorrect: Boolean
        get() = correctAnswerIndex == selectedAnswerIndex

}