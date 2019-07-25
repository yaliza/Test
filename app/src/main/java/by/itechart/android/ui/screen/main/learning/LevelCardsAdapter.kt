package by.itechart.android.ui.screen.main.learning

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itechart.android.R
import by.itechart.android.ext.hide
import by.itechart.android.ext.changeBackgroundColor
import by.itechart.android.ext.setBackgroundColor
import by.itechart.android.ext.show
import by.itechart.android.ui.entity.*
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_level_button.*
import kotlinx.android.synthetic.main.item_level_header.*
import kotlinx.android.synthetic.main.item_level_section.*


class LevelCardsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: List<LevelCardItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    object LevelCardType {
        const val HEADER = 0
        const val SECTION_SINGLE = 1
        const val SECTION_DOUBLE = 2
        const val BUTTON = 3
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is SectionSingleItem -> LevelCardType.SECTION_SINGLE
            is SectionDoubleItem -> LevelCardType.SECTION_DOUBLE
            is LevelHeaderItem -> LevelCardType.HEADER
            is LevelButtonItem -> LevelCardType.BUTTON
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LevelCardType.SECTION_SINGLE -> LevelSectionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_level_section_single, parent, false))
            LevelCardType.SECTION_DOUBLE -> LevelSectionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_level_section, parent, false))
            LevelCardType.HEADER -> LevelHeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_level_header, parent, false))
            LevelCardType.BUTTON -> LevelButtonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_level_button, parent, false))
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LevelHeaderViewHolder -> holder.bind(items[position] as LevelHeaderItem)
            is LevelSectionViewHolder -> holder.bind(items[position] as LevelSectionItem)
            is LevelButtonViewHolder -> holder.bind(items[position] as LevelButtonItem)
            else -> throw IllegalArgumentException("Unknown view holder type")
        }
    }

    override fun getItemCount() = items.size

    class LevelHeaderViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: LevelHeaderItem) {
            levelHeaderTextView.text = item.title
        }
    }

    class LevelButtonViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: LevelButtonItem) {
            val ctx = containerView.context
            if (item.isPassed) {
                medalImageView.show()
                lockImageView.hide()
                levelExaminationButton.text = ctx.getString(R.string.level_passed, item.passRate)
            } else {
                medalImageView.hide()
                lockImageView.show()
                levelExaminationButton.text = ctx.getString(R.string.level_not_passed)
            }
        }
    }

    class LevelSectionViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: LevelSectionItem) {
            sectionTitleTextView.text = item.title
            sectionRatingBar.rating = item.starCount.toFloat()
            sectionTopicNumTextView.text = sectionTopicNumTextView.context.getString(R.string.level_topics, item.topicCount)
            if (item.isStarted) {
                sectionTitleTextView.setBackgroundColor(item.color)
            } else {
                sectionTitleTextView.changeBackgroundColor(R.color.grey50)
            }
        }
    }
}