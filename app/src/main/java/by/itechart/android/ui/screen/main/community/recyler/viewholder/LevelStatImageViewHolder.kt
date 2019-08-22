package by.itechart.android.ui.screen.main.community.recyler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.itechart.android.ext.loadCircle
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_level_stat_image.*


class LevelStatImageViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(photo: String) {
        avatarImageView.loadCircle(photo)
    }

}