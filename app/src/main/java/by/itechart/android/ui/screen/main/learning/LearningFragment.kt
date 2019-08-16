package by.itechart.android.ui.screen.main.learning

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import by.itechart.android.R
import by.itechart.android.ext.hide
import by.itechart.android.ext.navigate
import by.itechart.android.ext.show
import by.itechart.android.ext.showMessage
import by.itechart.android.ui.base.ResourceObserver
import by.itechart.android.ui.entity.LevelUIModel
import by.itechart.android.ui.entity.LevelUIModel.Companion.TYPE_SECTION
import by.itechart.android.ui.screen.main.BottomNavFragmentDirections
import by.itechart.android.ui.screen.main.learning.recycler.LevelsAdapter
import kotlinx.android.synthetic.main.fragment_learning.*
import kotlinx.android.synthetic.main.view_progress_bar.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class LearningFragment : Fragment(R.layout.fragment_learning) {

    private val viewModel by viewModel<LearningViewModel>()
    private lateinit var levelsAdapter: LevelsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLevelsRecyclerView()

        viewModel.levelCards.observe(viewLifecycleOwner, object : ResourceObserver<List<LevelUIModel>>() {
            override fun onLoading() = progressBar.show()
            override fun onSuccess(data: List<LevelUIModel>?) {
                progressBar.hide()
                data?.let { levelsAdapter.items = data }
            }
            override fun onError(message: String) {
                showMessage(message)
                progressBar.hide()
            }
        })
    }

    private fun setupLevelsRecyclerView() {
        levelsAdapter = LevelsAdapter()
        levelsAdapter.apply {
            buttonClickListener = { navigate(BottomNavFragmentDirections.actionBottomNavFragmentToQuizFragment(it)) }
            sectionClickListener = { navigate(BottomNavFragmentDirections.actionBottomNavFragmentToModuleFragment(it)) }
        }

        val gridLayoutManager = GridLayoutManager(activity, 2)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int) =
                if (levelsAdapter.getItemViewType(position) == TYPE_SECTION) SPAN_NARROW else SPAN_WIDE
        }

        levelsRecyclerView.apply {
            adapter = levelsAdapter
            layoutManager = gridLayoutManager
        }
    }

    companion object {
        const val SPAN_NARROW = 1
        const val SPAN_WIDE = 2
    }

}