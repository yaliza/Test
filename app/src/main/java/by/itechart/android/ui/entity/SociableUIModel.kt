package by.itechart.android.ui.entity

import by.itechart.android.R


sealed class SociableUIModel(
    val name: String,
    val avatar: String
) {
    abstract val viewType: Int

    companion object {
        const val TYPE_MAN = R.layout.item_user
        const val TYPE_INVITATION = R.layout.item_invitation
    }

    override fun equals(other: Any?) =
        if (other is SociableUIModel) other.avatar == avatar && other.name == name else false
}

class UserUIModel(
    name: String,
    avatar: String
) : SociableUIModel(name, avatar) {
    override val viewType: Int = TYPE_MAN
}

class InvitationUiModel(
    name: String,
    avatar: String
) : SociableUIModel(name, avatar) {
    override val viewType: Int = TYPE_INVITATION
}