package by.itechart.android.ui.entity


data class QuestionResultUIModel(
    val rightIndex: Int,
    val answerComment: String,
    val choosedIndex: Int
) {

    val isRight: Boolean
        get() = rightIndex == choosedIndex

}