package by.itechart.android.ui.screen.main.learning.recycler.viewholder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.itechart.android.R
import by.itechart.android.ext.hide
import by.itechart.android.ext.show
import by.itechart.android.ui.entity.LevelButtonItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_level_button.*

class LevelButtonViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(item: LevelButtonItem) =
            containerView.context.let { ctx: Context ->
                if (item.isPassed) {
                    medalImageView.show()
                    lockImageView.hide()
                    levelExaminationButton.text = ctx.getString(R.string.level_passed, item.passRate)
                } else {
                    medalImageView.hide()
                    lockImageView.show()
                    levelExaminationButton.isClickable = false
                    levelExaminationButton.text = ctx.getString(R.string.level_not_passed)
                }
            }

}