package by.itechart.android.ui.screen.modal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itechart.android.R
import by.itechart.android.ui.entity.ModalCardUIModel
import by.itechart.android.ext.load
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_swipable_card.*


class ModalCardsAdapter : RecyclerView.Adapter<ModalCardsAdapter.CardViewHolder>() {

    var items: List<ModalCardUIModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_swipable_card, parent, false)
        return CardViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) = holder.bind(items[position])

    class CardViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(cardItem: ModalCardUIModel) {
            swipableCardTitleTextView.text = cardItem.title
            swipableCardDescriptionTextView.text = cardItem.description
            swipableCardImageView.load(cardItem.image)
        }
    }

}