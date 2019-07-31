package by.itechart.android.ui.screen.main.learning.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itechart.android.ui.entity.*
import by.itechart.android.ui.entity.LevelUIModel.Companion.TYPE_BUTTON
import by.itechart.android.ui.entity.LevelUIModel.Companion.TYPE_HEADER
import by.itechart.android.ui.entity.LevelUIModel.Companion.TYPE_SECTION
import by.itechart.android.ui.entity.LevelUIModel.Companion.TYPE_SECTION_WIDE
import by.itechart.android.ui.screen.main.learning.recycler.viewholder.LevelButtonViewHolder
import by.itechart.android.ui.screen.main.learning.recycler.viewholder.LevelHeaderViewHolder
import by.itechart.android.ui.screen.main.learning.recycler.viewholder.LevelSectionViewHolder
import kotlinx.android.synthetic.main.item_level_button.view.*
import kotlinx.android.synthetic.main.item_level_section.view.*


class LevelsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var buttonClickListener: ((String) -> Unit)? = null
    var sectionClickListener: ((String) -> Unit)? = null

    var items: List<LevelUIModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int) = items[position].viewType

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            TYPE_HEADER -> LevelHeaderViewHolder(view)
            TYPE_SECTION_WIDE, TYPE_SECTION ->
                LevelSectionViewHolder(view).apply {
                    view.setOnClickListener { sectionClickListener?.invoke(view.sectionTitleTextView.text.toString()) }
                }
            TYPE_BUTTON ->
                LevelButtonViewHolder(view).apply {
                    view.levelExaminationButton.setOnClickListener {
                        buttonClickListener?.invoke((items[adapterPosition] as LevelButtonUIModel).passRate.toString())
                    }
                }
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = when (holder) {
        is LevelHeaderViewHolder -> holder.bind(items[position] as LevelHeaderUIModel)
        is LevelSectionViewHolder -> holder.bind(items[position] as LevelSectionUIModel)
        is LevelButtonViewHolder -> holder.bind(items[position] as LevelButtonUIModel)
        else -> throw IllegalArgumentException("Unknown view holder type")
    }

    override fun getItemCount() = items.size

}