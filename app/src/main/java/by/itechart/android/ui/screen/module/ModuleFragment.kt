package by.itechart.android.ui.screen.module

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.itechart.android.R
import by.itechart.android.ext.hide
import by.itechart.android.ext.show
import by.itechart.android.ext.showMessage
import by.itechart.android.ui.base.ResourceObserver
import by.itechart.android.ui.entity.TopicUIModel
import kotlinx.android.synthetic.main.fragment_module.*
import kotlinx.android.synthetic.main.view_back_button.*
import kotlinx.android.synthetic.main.view_progress_bar.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ModuleFragment : Fragment(R.layout.fragment_module) {

    private val args: ModuleFragmentArgs by navArgs()
    private val viewModel by viewModel<ModuleViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backImageView.setOnClickListener { activity?.let { findNavController(it, R.id.primaryNavHostFragment).navigateUp() } }
        sectionNameTextView.text = args.sectionName
        setupTopicsRecyclerView()
    }

    private fun setupTopicsRecyclerView() {
        val topicsAdapter = TopicCardsAdapter()
        topicsAdapter.topicClickListener = { showMessage(it) }

        topicsRecyclerView.apply {
            adapter = topicsAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        viewModel.topicItems.observe(viewLifecycleOwner, object : ResourceObserver<List<TopicUIModel>>() {
            override fun onLoading() = progressBar.show()
            override fun onSuccess(data: List<TopicUIModel>?) {
                progressBar.hide()
                data?.let { topicsAdapter.items = it }
            }
            override fun onError(message: String) {
                showMessage(message)
                progressBar.hide()
            }
        })
    }

}