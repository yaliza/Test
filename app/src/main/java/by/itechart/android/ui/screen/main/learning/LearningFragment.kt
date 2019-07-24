package by.itechart.android.ui.screen.main.learning

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import by.itechart.android.R
import by.itechart.android.ext.hide
import by.itechart.android.ext.show
import by.itechart.android.ui.base.ResourceObserver
import by.itechart.android.ui.entity.LevelCardItem
import kotlinx.android.synthetic.main.fragment_school.*
import kotlinx.android.synthetic.main.view_progress_bar.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class LearningFragment : Fragment(R.layout.fragment_school) {

    private val viewModel by viewModel<LearningViewModel>()
    private lateinit var levelCardsAdapter: LevelCardsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLevelsRecyclerView()

        viewModel.levelCards.observe(viewLifecycleOwner, object : ResourceObserver<List<LevelCardItem>>() {
            override fun onSuccess(data: List<LevelCardItem>?) {
                progressBar.hide()
                data?.let { levelCardsAdapter.items = data }
            }
            override fun onLoading() = progressBar.show()
            override fun onError(message: String) {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                progressBar.hide()
            }
        })
    }

    private fun setupLevelsRecyclerView() {
        levelCardsAdapter = LevelCardsAdapter()
        val gridLayoutManager = GridLayoutManager(activity, 2)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (levelCardsAdapter.getItemViewType(position)) {
                    LevelCardsAdapter.LevelCardType.HEADER -> 2
                    LevelCardsAdapter.LevelCardType.BUTTON -> 2
                    LevelCardsAdapter.LevelCardType.SECTION_SINGLE -> 2
                    LevelCardsAdapter.LevelCardType.SECTION_DOUBLE -> 1
                    else -> throw IllegalArgumentException("Unknown view type")
                }
            }
        }

        levelsRecyclerView.apply {
            adapter = levelCardsAdapter
            layoutManager = gridLayoutManager
        }
    }

}