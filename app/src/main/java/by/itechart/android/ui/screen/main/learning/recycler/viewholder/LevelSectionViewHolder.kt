package by.itechart.android.ui.screen.main.learning.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.itechart.android.R
import by.itechart.android.ext.setBackgroundColor
import by.itechart.android.ext.setBackgroundColorRes
import by.itechart.android.ui.entity.LevelSectionItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_level_section.*

class LevelSectionViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(item: LevelSectionItem) {
        sectionTitleTextView.text = item.title
        ratingBar.rating = item.starCount.toFloat()
        topicNumTextView.text =
                topicNumTextView.context.getString(R.string.level_topics, item.topicCount)
        if (item.isStarted) {
            sectionTitleTextView.setBackgroundColor(item.color)
        } else {
            sectionTitleTextView.setBackgroundColorRes(R.color.grey50)
        }
    }

}