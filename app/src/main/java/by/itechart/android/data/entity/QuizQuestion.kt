package by.itechart.android.data.entity


class QuizQuestion(
    val question: String = "",
    val answers: List<String> = emptyList(),
    val answerComment: String = "",
    val photoUrl: String? = null,
    val indexOfCorrect: Int = 0
)