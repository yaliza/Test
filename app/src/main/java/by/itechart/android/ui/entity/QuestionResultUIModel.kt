package by.itechart.android.ui.entity


class QuestionResultUIModel(
    val rightIndex: Int,
    val answerComment: String,
    val choosedIndex: Int
) {

    val isRight: Boolean
        get() = rightIndex == choosedIndex

}