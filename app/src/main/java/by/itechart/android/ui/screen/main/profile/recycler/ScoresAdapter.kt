package by.itechart.android.ui.screen.main.profile.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itechart.android.R
import by.itechart.android.ui.entity.ScoreUIModel
import by.itechart.android.ui.screen.main.profile.recycler.viewholder.ScoreViewHolder


class ScoresAdapter : RecyclerView.Adapter<ScoreViewHolder>() {

    var items: List<ScoreUIModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ScoreViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_score, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) = holder.bind(items[position])
}