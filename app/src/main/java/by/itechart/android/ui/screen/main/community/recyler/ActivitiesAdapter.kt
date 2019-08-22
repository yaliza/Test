package by.itechart.android.ui.screen.main.community.recyler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itechart.android.R
import by.itechart.android.data.entity.Activity
import by.itechart.android.ui.screen.main.community.recyler.viewholder.ActivityViewHolder


class ActivitiesAdapter : RecyclerView.Adapter<ActivityViewHolder>() {

    var items: List<Activity> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) =
        holder.bind(items[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ActivityViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_activity, parent, false))
}