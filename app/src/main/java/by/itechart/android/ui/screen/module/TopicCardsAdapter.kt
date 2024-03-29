package by.itechart.android.ui.screen.module

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.itechart.android.R
import by.itechart.android.data.entity.Topic
import by.itechart.android.ext.hide
import by.itechart.android.ext.show
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_topic.*


class TopicCardsAdapter : RecyclerView.Adapter<TopicCardsAdapter.TopicViewHolder>() {

    var topicClickListener: ((String) -> Unit)? = null

    var items: List<Topic> = emptyList()
        set(value) {
            val diffResult = DiffUtil.calculateDiff(TopicDiffUtilCallback(field, value))
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_topic, parent, false)
        return TopicViewHolder(view).apply {
            view.setOnClickListener { topicClickListener?.invoke(items[adapterPosition].title) }
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) =
        holder.bind(items[position])

    class TopicViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(topicItem: Topic) {
            topicNameTextView.text = topicItem.title
            if (topicItem.isPassed) {
                passedImageView.show()
                playImageView.hide()
            } else {
                passedImageView.hide()
                playImageView.show()
            }
        }

    }
}