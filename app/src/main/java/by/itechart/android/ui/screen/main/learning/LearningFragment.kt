package by.itechart.android.ui.screen.main.learning

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import by.itechart.android.R
import by.itechart.android.ext.hide
import by.itechart.android.ext.show
import by.itechart.android.ext.showMessage
import by.itechart.android.ui.base.ResourceObserver
import by.itechart.android.ui.entity.LevelItem
import by.itechart.android.ui.entity.LevelItem.Companion.TYPE_SECTION_DOUBLE
import by.itechart.android.ui.screen.main.learning.recycler.LevelCardsAdapter
import kotlinx.android.synthetic.main.fragment_learning.*
import kotlinx.android.synthetic.main.view_progress_bar.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class LearningFragment : Fragment(R.layout.fragment_learning) {

    private val viewModel by viewModel<LearningViewModel>()
    private lateinit var levelCardsAdapter: LevelCardsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLevelsRecyclerView()

        viewModel.levelCards.observe(
                viewLifecycleOwner,
                object : ResourceObserver<List<LevelItem>>() {
                    override fun onSuccess(data: List<LevelItem>?) {
                        progressBar.hide()
                        data?.let { levelCardsAdapter.items = data }
                    }

                    override fun onLoading() = progressBar.show()
                    override fun onError(message: String) {
                        showMessage(message)
                        progressBar.hide()
                    }
                })
    }

    private fun setupLevelsRecyclerView() {
        levelCardsAdapter = LevelCardsAdapter()
        levelCardsAdapter.apply {
            buttonClickListener = { showMessage(it) }
            sectionClickListener = { showMessage(it) }
        }

        val gridLayoutManager = GridLayoutManager(activity, 2)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int) = if (levelCardsAdapter.getItemViewType(position) == TYPE_SECTION_DOUBLE) 1 else 2
        }

        levelsRecyclerView.apply {
            adapter = levelCardsAdapter
            layoutManager = gridLayoutManager
        }
    }

}