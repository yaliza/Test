package by.itechart.android.ui.screen.main.community.recyler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.itechart.android.data.entity.Stat
import by.itechart.android.ext.load
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_stat.*


class StatViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(stat: Stat) {
        statTitleTextView.text = stat.title
        statDescriptionTextView.text = stat.description
        statImageView.load(stat.photo)
    }

}