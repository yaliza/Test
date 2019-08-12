package by.itechart.android.ui.mapper

import by.itechart.android.R
import by.itechart.android.data.entity.ModalCard
import by.itechart.android.ui.entity.ModalCardUIModel


class ModalCardMapper {

    fun map(modalCards: List<ModalCard>) =
        mutableListOf<ModalCardUIModel>().apply {
            modalCards.forEach { add(ModalCardUIModel(it.title, it.description, it.image, getLayoutType(it.type))) }
        }

    private fun getLayoutType(type: String) =
        when (type) {
            "type1" -> R.layout.view_swiped_type1
            else -> R.layout.view_swiped_type2
        }
}