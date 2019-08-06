package by.itechart.android.ui.screen.main.profile.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.itechart.android.ext.loadCircle
import by.itechart.android.ui.entity.InvitationUiModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_invitation.*

class InvitationViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bind(person: InvitationUiModel) {
        userAvatarImageView.loadCircle(person.avatar)
        userNameTextView.text = person.name
    }

}