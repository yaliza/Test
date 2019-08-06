package by.itechart.android.ui.screen.main.profile.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.itechart.android.ui.entity.CertificateUIModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_certificate.*

class CertificateViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(certificate: CertificateUIModel) {
        certificateHeaderView.setBackgroundResource(certificate.headerColor)
        certificateHeaderTextView.text = certificate.title
        dateTextView.text = certificate.date
        nameTextView.text = certificate.name
    }

}