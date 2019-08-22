package by.itechart.android.ui.screen.main.community.recyler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itechart.android.R
import by.itechart.android.data.entity.Stat
import by.itechart.android.ui.screen.main.community.recyler.viewholder.StatViewHolder


class StatsAdapter : RecyclerView.Adapter<StatViewHolder>() {

    var items: List<Stat> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var statClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_stat, parent, false)
        return StatViewHolder(view).apply {
            view.setOnClickListener { statClickListener?.invoke(items[adapterPosition].title) }
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: StatViewHolder, position: Int) =
        holder.bind(items[position])

}