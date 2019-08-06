package by.itechart.android.ui.screen.main.profile.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.itechart.android.ext.loadCircle
import by.itechart.android.ui.entity.UserUIModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.*

class UserViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(person: UserUIModel) {
        userAvatarImageView.loadCircle(person.avatar)
        userNameTextView.text = person.name
    }

}
