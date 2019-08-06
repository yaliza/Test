package by.itechart.android.ui.screen.main.profile.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.itechart.android.ext.load
import by.itechart.android.ui.entity.ScoreUIModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_score.*


class ScoreViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(item: ScoreUIModel) {
        scoreImageView.load(item.picture)
        scoresTextView.text = item.title
    }

}