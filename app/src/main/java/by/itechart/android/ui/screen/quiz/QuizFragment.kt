package by.itechart.android.ui.screen.quiz

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import by.itechart.android.R
import by.itechart.android.data.entity.QuizQuestion
import by.itechart.android.ext.*
import by.itechart.android.ui.base.ResourceObserver
import by.itechart.android.ui.entity.QuestionResultUIModel
import by.itechart.android.ui.view.ViewOption
import kotlinx.android.synthetic.main.fragment_quiz.*
import kotlinx.android.synthetic.main.view_back_button.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class QuizFragment : Fragment(R.layout.fragment_quiz) {

    private val viewModel by viewModel<QuizViewModel>()
    private val args: QuizFragmentArgs by navArgs()
    private lateinit var options: List<ViewOption>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backImageView.setOnClickListener { navigateUp() }

        val optionClickListener =
            View.OnClickListener { vi: View -> options.indexOfFirst { vi.id == it.id }.let { viewModel.selected = it } }
        options = listOf(option1, option2, option3, option4)
        options.forEach { it.setOnClickListener(optionClickListener) }

        transparentView.setOnClickListener { }
        quizButton.setOnClickListener { viewModel.submit() }

        with(viewModel) {
            levelId = args.levelId

            progress.observe(
                viewLifecycleOwner,
                Observer { progressTextView.text = getString(R.string.quiz_progress, it.first, it.second) })

            quizResult.observe(
                viewLifecycleOwner,
                Observer { navigate(QuizFragmentDirections.actionQuizFragmentToQuizResultFragment(it)) })

            selectOption.observe(viewLifecycleOwner, Observer { options[it].isBordered = true })
            unselectOption.observe(viewLifecycleOwner, Observer { options[it].isBordered = false })

            question.observe(viewLifecycleOwner, object : ResourceObserver<QuizQuestion>() {
                override fun onSuccess(data: QuizQuestion?) = data?.let { setupQuestion(it) }
                override fun onLoading() {}
                override fun onError(message: String) = showMessage(message)
            })
            questionResult.observe(viewLifecycleOwner, Observer { setupQuestionResult(it) })
        }
    }

    private fun setupQuestion(question: QuizQuestion) =
        with(question) {
            questionTextView.text = this.question
            quizImageView.load(photoUrl)
            options.forEachIndexed { index, option -> option.title = answers[index] }
        }

    private fun setupQuestionResult(questionResultUIModel: QuestionResultUIModel?) =
        questionResultUIModel?.run {
            quizButton.text = getString(R.string.quiz_next)
            transparentView.show()
            answerCardView.show()
            answerTitleTextView.text =
                if (isRight) getString(R.string.quiz_correct) else getString(R.string.quiz_incorrect)
            answerDescriptionTextView.text = answerComment
            options[rightIndex].translationZ = resources.getDimension(R.dimen.quiz_translation_z_background_above)
        } ?: run {
            quizButton.text = getString(R.string.quiz_submit)
            transparentView.hide()
            answerCardView.hide()
            options.forEach { it.translationZ = resources.getDimension(R.dimen.quiz_translation_z_background_below) }
        }
}