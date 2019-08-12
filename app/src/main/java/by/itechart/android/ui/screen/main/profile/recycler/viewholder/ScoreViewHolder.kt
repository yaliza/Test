package by.itechart.android.ui.screen.main.profile.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.itechart.android.data.entity.Score
import by.itechart.android.ext.load
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_score.*


class ScoreViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(item: Score) {
        scoreImageView.load(item.picture)
        scoresTextView.text = item.title
    }

}