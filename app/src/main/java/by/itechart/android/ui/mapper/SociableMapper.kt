package by.itechart.android.ui.mapper

import by.itechart.android.data.entity.Sociable
import by.itechart.android.data.entity.SociableType
import by.itechart.android.ui.entity.InvitationUiModel
import by.itechart.android.ui.entity.SociableUIModel
import by.itechart.android.ui.entity.UserUIModel


class SociableMapper {

    fun map(persons: List<Sociable>):List<SociableUIModel> =
        mutableListOf<SociableUIModel>().apply {
            persons.forEach { sociable: Sociable ->
                when (sociable.type) {
                    SociableType.FOLLOWER, SociableType.FOLLOWING -> add(UserUIModel(sociable.name, sociable.avatar))
                    SociableType.INVITE -> add(InvitationUiModel(sociable.name, sociable.avatar))
                }
            }
        }

}