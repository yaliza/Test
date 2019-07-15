package by.itechart.android.data.entities

import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes

class CardItem(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
    @LayoutRes val layout: Int
)