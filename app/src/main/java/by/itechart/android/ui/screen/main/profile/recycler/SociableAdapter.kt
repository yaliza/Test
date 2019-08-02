package by.itechart.android.ui.screen.main.profile.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itechart.android.ui.entity.InvitationUiModel
import by.itechart.android.ui.entity.SociableUIModel
import by.itechart.android.ui.entity.UserUIModel
import by.itechart.android.ui.screen.main.profile.recycler.viewholder.InvitationViewHolder
import by.itechart.android.ui.screen.main.profile.recycler.viewholder.UserViewHolder
import kotlinx.android.synthetic.main.item_invitation.view.*


class SociableAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<SociableUIModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var userClickListener: ((SociableUIModel) -> Unit)? = null
    var invitationClickListener: ((InvitationUiModel, Boolean) -> Unit)? = null

    override fun getItemViewType(position: Int) = items[position].viewType

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            SociableUIModel.TYPE_MAN ->
                UserViewHolder(view).apply {
                    view.setOnClickListener { userClickListener?.invoke(items[adapterPosition]) }
                }
            SociableUIModel.TYPE_INVITATION -> InvitationViewHolder(view).apply {
                view.setOnClickListener { userClickListener?.invoke(items[adapterPosition]) }
                view.apply {
                    acceptButton.setOnClickListener {
                        invitationClickListener?.invoke(items[adapterPosition] as InvitationUiModel, true)
                    }
                    declineButton.setOnClickListener {
                        invitationClickListener?.invoke(items[adapterPosition] as InvitationUiModel, false)
                    }
                }
            }
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder) {
            is InvitationViewHolder -> holder.bind(items[position] as InvitationUiModel)
            is UserViewHolder -> holder.bind(items[position] as UserUIModel)
            else -> throw IllegalArgumentException("Unknown view holder type")
        }
}