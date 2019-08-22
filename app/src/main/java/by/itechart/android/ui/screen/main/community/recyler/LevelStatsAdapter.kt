package by.itechart.android.ui.screen.main.community.recyler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itechart.android.R
import by.itechart.android.ui.base.HorizontalOverlappingItemDecorator
import by.itechart.android.ui.entity.LevelStatUIModel
import by.itechart.android.ui.screen.main.community.recyler.viewholder.LevelStatViewHolder
import kotlinx.android.synthetic.main.item_level_stat.view.*


class LevelStatsAdapter(
    private val colors: IntArray,
    private val imageOverlapping: Float
) : RecyclerView.Adapter<LevelStatViewHolder>() {

    var items: List<LevelStatUIModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        with(LayoutInflater.from(parent.context).inflate(R.layout.item_level_stat, parent, false)) {
            with(levelStatImagesRecyclerView) {
                adapter = LevelStatImagesAdapter()
                addItemDecoration(HorizontalOverlappingItemDecorator(imageOverlapping))
            }
            LevelStatViewHolder(this)
        }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: LevelStatViewHolder, position: Int) {
        holder.bind(colors[colors.size - 1 - position % colors.size], items[position])
        (holder.containerView.levelStatImagesRecyclerView.adapter as LevelStatImagesAdapter).items =
            items[position].photos
    }

}