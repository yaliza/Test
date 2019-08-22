package by.itechart.android.ui.screen.main.community.recyler.viewholder

import android.content.res.ColorStateList
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.itechart.android.ui.entity.LevelStatUIModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_level_stat.*


class LevelStatViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(color : Int, levelStatUIModel: LevelStatUIModel) {
        coloredView.backgroundTintList = ColorStateList.valueOf(color)
        levelDescriptionTextView.text = levelStatUIModel.description
        levelTextView.text = levelStatUIModel.title
    }

}