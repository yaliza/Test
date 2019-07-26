package by.itechart.android.ui.screen.main.learning.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itechart.android.ui.entity.LevelButtonItem
import by.itechart.android.ui.entity.LevelHeaderItem
import by.itechart.android.ui.entity.LevelItem
import by.itechart.android.ui.entity.LevelItem.Companion.TYPE_BUTTON
import by.itechart.android.ui.entity.LevelItem.Companion.TYPE_HEADER
import by.itechart.android.ui.entity.LevelItem.Companion.TYPE_SECTION_DOUBLE
import by.itechart.android.ui.entity.LevelItem.Companion.TYPE_SECTION_SINGLE
import by.itechart.android.ui.entity.LevelSectionItem
import by.itechart.android.ui.screen.main.learning.recycler.viewholder.LevelButtonViewHolder
import by.itechart.android.ui.screen.main.learning.recycler.viewholder.LevelHeaderViewHolder
import by.itechart.android.ui.screen.main.learning.recycler.viewholder.LevelSectionViewHolder
import kotlinx.android.synthetic.main.item_level_button.view.*
import kotlinx.android.synthetic.main.item_level_section.view.*


class LevelCardsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var buttonClickListener: ((String) -> Unit)? = null
    var sectionClickListener: ((String) -> Unit)? = null

    var items: List<LevelItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int) = items[position].viewType

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            LayoutInflater.from(parent.context).inflate(viewType, parent, false).let { view ->
                when (viewType) {
                    TYPE_HEADER -> LevelHeaderViewHolder(view)
                    TYPE_SECTION_SINGLE, TYPE_SECTION_DOUBLE ->
                        LevelSectionViewHolder(view).apply {
                            view.setOnClickListener { view: View -> sectionClickListener?.invoke(view.sectionTitleTextView.text.toString()) }
                        }
                    TYPE_BUTTON ->
                        LevelButtonViewHolder(view).apply {
                            view.levelExaminationButton.setOnClickListener {
                                buttonClickListener?.invoke((items[adapterPosition] as LevelButtonItem).passRate.toString())
                            }
                        }
                    else -> throw IllegalArgumentException("Unknown view type")
                }
            }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
            when (holder) {
                is LevelHeaderViewHolder -> holder.bind(items[position] as LevelHeaderItem)
                is LevelSectionViewHolder -> holder.bind(items[position] as LevelSectionItem)
                is LevelButtonViewHolder -> holder.bind(items[position] as LevelButtonItem)
                else -> throw IllegalArgumentException("Unknown view holder type")
            }


    override fun getItemCount() = items.size
}