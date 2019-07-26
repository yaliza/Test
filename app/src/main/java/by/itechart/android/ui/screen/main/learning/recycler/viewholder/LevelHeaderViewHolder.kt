package by.itechart.android.ui.screen.main.learning.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.itechart.android.ui.entity.LevelHeaderItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_level_header.*

class LevelHeaderViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(item: LevelHeaderItem) {
        levelHeaderTextView.text = item.title
    }

}