package by.itechart.android.ui.entity

import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes

class ModalCardItem(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
    @LayoutRes val layout: Int
)