package by.itechart.android.ui.screen.main.learning.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.itechart.android.ext.setBackgroundColorRes
import by.itechart.android.ui.entity.LevelSectionUIModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_level_section.*


class LevelSectionViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(item: LevelSectionUIModel) {
        sectionTitleTextView.text = item.title
        ratingBar.rating = item.starCount.toFloat()
        topicsCountTextView.text = item.topicTitle
        sectionTitleTextView.setBackgroundColorRes(item.color)
    }

}