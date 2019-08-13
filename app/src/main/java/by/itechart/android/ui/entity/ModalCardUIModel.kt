package by.itechart.android.ui.entity

import androidx.annotation.LayoutRes

data class ModalCardUIModel(
    val title: String,
    val description: String,
    val image: String,
    @LayoutRes val layout: Int
)