package by.itechart.android.ui.screen.main.community

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import by.itechart.android.R
import by.itechart.android.data.entity.Activity
import by.itechart.android.data.entity.Stat
import by.itechart.android.ext.showMessage
import by.itechart.android.ui.base.DividerItemDecoration
import by.itechart.android.ui.base.ResourceObserver
import by.itechart.android.ui.entity.LevelStatUIModel
import by.itechart.android.ui.screen.main.community.recyler.ActivitiesAdapter
import by.itechart.android.ui.screen.main.community.recyler.LevelStatsAdapter
import by.itechart.android.ui.screen.main.community.recyler.StatsAdapter
import kotlinx.android.synthetic.main.fragment_community.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class CommunityFragment : Fragment(R.layout.fragment_community) {

    private val viewModel by viewModel<CommunityViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        upButton.setOnClickListener {
            if (pyramidView.userLevel != 6) {
                pyramidView.userLevel++
            }
        }
        downButton.setOnClickListener {
            if (pyramidView.userLevel != 1) {
                pyramidView.userLevel--
            }
        }

        val levelStatsAdapter = LevelStatsAdapter(
            resources.getIntArray(R.array.pyramidColors),
            -resources.getDimension(R.dimen.level_stat_image_overlapping)
        )
        with(levelStatRecyclerView) {
            adapter = levelStatsAdapter
            addItemDecoration(DividerItemDecoration(context))
        }

        val activityAdapter = ActivitiesAdapter()
        with(activityRecyclerView) {
            adapter = activityAdapter
            addItemDecoration(DividerItemDecoration(context))
        }

        val statsAdapter = StatsAdapter().apply {
            statRecyclerView.adapter = this
            statClickListener = { showMessage(it) }
        }

        activitySeeAllButton.setOnClickListener { showMessage("See all community") }
        statSeeAllButton.setOnClickListener { showMessage("View all stats") }

        with(viewModel) {
            profile.observe(viewLifecycleOwner, Observer { pyramidView.photoUrl = it.photoUrl })
            stats.observe(viewLifecycleOwner, object : ResourceObserver<List<Stat>>() {
                override fun onError(message: String) = showMessage(message)
                override fun onLoading() {}
                override fun onSuccess(data: List<Stat>?) =
                    data?.let { statsAdapter.items = it }
            })
            levelStats.observe(
                viewLifecycleOwner,
                object : ResourceObserver<List<LevelStatUIModel>>() {
                    override fun onError(message: String) = showMessage(message)
                    override fun onLoading() {}
                    override fun onSuccess(data: List<LevelStatUIModel>?) =
                        data?.let { levelStatsAdapter.items = it }
                })
            activities.observe(viewLifecycleOwner, object : ResourceObserver<List<Activity>>() {
                override fun onLoading() {}
                override fun onError(message: String) = showMessage(message)
                override fun onSuccess(data: List<Activity>?) =
                    data?.let { activityAdapter.items = it }
            })
        }
    }
}