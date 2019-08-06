package by.itechart.android.ui.screen.main.profile.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itechart.android.R
import by.itechart.android.ui.entity.CertificateUIModel
import by.itechart.android.ui.screen.main.profile.recycler.viewholder.CertificateViewHolder


class CertificatesAdapter : RecyclerView.Adapter<CertificateViewHolder>() {

    var items: List<CertificateUIModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CertificateViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_certificate, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CertificateViewHolder, position: Int) = holder.bind(items[position])
}