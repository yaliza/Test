package by.itechart.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itechart.android.data.entities.CardItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_swipable_card.*


class CardsAdapter : RecyclerView.Adapter<CardsAdapter.CardViewHolder>() {

    var items: List<CardItem> by didSet(listOf()) { notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_swipable_card, parent, false)
        return CardViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) = holder.bind(items[position])

    class CardViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(cardItem: CardItem) {
            swipableCardTitleTextView.text = cardItem.title
            swipableCardDescriptionTextView.text = cardItem.description
            swipableCardImageView.load(cardItem.image)
        }
    }
}