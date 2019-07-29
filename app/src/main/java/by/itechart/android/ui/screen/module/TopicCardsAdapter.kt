package by.itechart.android.ui.screen.module

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itechart.android.R
import by.itechart.android.ext.hide
import by.itechart.android.ext.show
import by.itechart.android.ui.entity.TopicItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_topic.*


class TopicCardsAdapter : RecyclerView.Adapter<TopicCardsAdapter.TopicViewHolder>() {

    var topicClickListener: ((String) -> Unit)? = null

    var items: List<TopicItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_topic, parent, false
        ).let { view: View ->
            TopicViewHolder(view).apply {
                view.setOnClickListener {
                    topicClickListener?.invoke(items[adapterPosition].title)
                }
            }
        }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) =
        holder.bind(items[position])

    class TopicViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(topicItem: TopicItem) {
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