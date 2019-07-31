package by.itechart.android.ui.entity

import by.itechart.android.R
import by.itechart.android.data.entity.Section

sealed class LevelUIModel {
    abstract val viewType: Int

    companion object {
        const val TYPE_HEADER = R.layout.item_level_header
        const val TYPE_SECTION_WIDE = R.layout.item_level_section_wide
        const val TYPE_SECTION = R.layout.item_level_section
        const val TYPE_BUTTON = R.layout.item_level_button
    }
}

class LevelSectionUIModel(
    val title: String,
    val topicCount: Int,
    val starCount: Int,
    val color: String,
    override val viewType: Int
) : LevelUIModel() {

    constructor(topic: Section, color: String, viewType: Int = TYPE_SECTION) : this(
        topic.title,
        topic.topicCount,
        topic.starCount,
        color,
        viewType
    )

    val isStarted
        get() = starCount != 0
}

class LevelHeaderUIModel(val title: String) : LevelUIModel() {
    override val viewType: Int = TYPE_HEADER
}

class LevelButtonUIModel(val passRate: Int) : LevelUIModel() {
    override val viewType: Int = TYPE_BUTTON
    val isPassed
        get() = passRate != 0
}