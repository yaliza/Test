package by.itechart.android.ui.screen.quiz

import androidx.lifecycle.MutableLiveData
import by.itechart.android.data.entity.QuizQuestion
import by.itechart.android.data.repository.Repository
import by.itechart.android.ui.base.BaseViewModel
import by.itechart.android.ui.base.Resource
import by.itechart.android.ui.entity.QuestionResultUIModel
import by.itechart.android.ui.entity.QuizResultUIModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class QuizViewModel(private val repository: Repository) : BaseViewModel() {

    private lateinit var questions: List<QuizQuestion>
    private var currentQuestionIndex = 0
    private var correctNumber = 0
    private val isQuestionSubmitted: Boolean
        get() = questionResult.value != null

    val question = MutableLiveData<Resource<QuizQuestion>>()
    val questionResult = MutableLiveData<QuestionResultUIModel?>()

    val selectOption = MutableLiveData<Int>()
    val unselectOption = MutableLiveData<Int>()

    val progress = MutableLiveData<Pair<Int, Int>>()
    val quizResult = MutableLiveData<QuizResultUIModel>()

    var selected: Int? = null
        set(value) {
            if(value != field){
                field?.let { unselectOption.value = field }
                field = value
                field?.let { selectOption.value = field }
            }
        }

    var levelId: String = ""
        set(value) {
            if (value != levelId) {
                field = value
                loadQuestions()
            }
        }

    fun submit() =
        if (isQuestionSubmitted) {
            nextQuestion()
        } else {
            selected?.let {
                computeQuestionResult(it)
                selected = questions[currentQuestionIndex].indexOfCorrect
            }
        }

    private fun computeQuestionResult(userOption: Int) =
        with(questions[currentQuestionIndex]) {
            if (userOption == indexOfCorrect) correctNumber++
            questionResult.value = QuestionResultUIModel(indexOfCorrect, answerComment, userOption)
        }

    private fun nextQuestion() {
        currentQuestionIndex++
        if (currentQuestionIndex < questions.size) {
            progress.value = Pair(currentQuestionIndex + 1, questions.size)
            selected = null
            questionResult.value = null
            question.value = Resource.Success(questions[currentQuestionIndex])
        } else {
            quizResult.value = QuizResultUIModel(correctNumber, questions.size, levelId)
        }
    }

    private fun loadQuestions() =
        repository.getQuestions(levelId)
            .doOnSubscribe { question.postValue(Resource.Loading()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { items ->
                    questions = items
                    currentQuestionIndex = 0
                    progress.value = Pair(currentQuestionIndex + 1, questions.size)
                    questionResult.value = null
                    question.value = Resource.Success(items[currentQuestionIndex])
                },
                { error -> question.value = Resource.Error(error) }
            )
            .addToDisposables()

}