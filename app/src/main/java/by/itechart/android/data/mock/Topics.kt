package by.itechart.android.data.mock

import by.itechart.android.ui.entity.TopicUIModel


object Topics {

    val mock = listOf(
        TopicUIModel(
            "Introduction",
            true
        ),
        TopicUIModel(
            "Ask Price, Bid Price",
            true
        ),
        TopicUIModel(
            "Pip, Points, Ticks",
            true
        ),
        TopicUIModel(
            "Lot, Margin",
            true
        ),
        TopicUIModel(
            "Pip Price",
            false
        )
    )

}