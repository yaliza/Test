package by.itechart.android.ui.screen.main.community.recyler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itechart.android.R
import by.itechart.android.ui.screen.main.community.recyler.viewholder.LevelStatImageViewHolder


class LevelStatImagesAdapter : RecyclerView.Adapter<LevelStatImageViewHolder>() {

    var items: List<String> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LevelStatImageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_level_stat_image,
                parent,
                false
            )
        )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: LevelStatImageViewHolder, position: Int) =
        holder.bind(items[position])

}