package by.itechart.android.ui.screen.main.community.recyler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.itechart.android.data.entity.Activity
import by.itechart.android.ext.load
import by.itechart.android.ext.loadRounded
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_activity.*


class ActivityViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(activity: Activity): Unit =
        with(activity) {
            achievementTextView.text = description
            nameTextView.text = name
            avatarImageView.loadRounded(photo)
            countryImageView.load(flag)
        }

}