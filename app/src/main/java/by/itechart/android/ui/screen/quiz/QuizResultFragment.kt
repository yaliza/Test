package by.itechart.android.ui.screen.quiz

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.itechart.android.R
import kotlinx.android.synthetic.main.fragment_quiz_result.*
import kotlinx.android.synthetic.main.item_certificate.*
import kotlinx.android.synthetic.main.view_back_button.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class QuizResultFragment : Fragment(R.layout.fragment_quiz_result) {

    private val args: QuizResultFragmentArgs by navArgs()
    val viewModel by viewModel<QuizResultViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            quizEndingUIModel = args.quizResult
            result.observe(viewLifecycleOwner, Observer {
                resultTextView.text = getString(R.string.quiz_correct_result, it.correct, it.questions)
                resultRatingBar.rating = it.rating.toFloat()
            })
            certificate.observe(viewLifecycleOwner, Observer {
                dateTextView.text = it.date
                nameTextView.text = it.name
                certificateHeaderTextView.text = it.title
                certificateHeaderView.setBackgroundResource(it.headerColor)
            })
        }

        backImageView.setOnClickListener { findNavController().navigateUp() }
        learningButton.setOnClickListener { findNavController().navigateUp() }
    }

}