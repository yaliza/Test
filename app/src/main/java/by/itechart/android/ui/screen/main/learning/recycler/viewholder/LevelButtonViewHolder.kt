package by.itechart.android.ui.screen.main.learning.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.itechart.android.ext.hide
import by.itechart.android.ext.show
import by.itechart.android.ui.entity.LevelButtonUIModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_level_button.*


class LevelButtonViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(item: LevelButtonUIModel) =
        if (item.isPassed) {
            medalImageView.show()
            lockImageView.hide()
            levelExaminationButton.text = item.title
        } else {
            medalImageView.hide()
            lockImageView.show()
            levelExaminationButton.isClickable = false
            levelExaminationButton.text = item.title
        }

}