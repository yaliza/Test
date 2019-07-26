package by.itechart.android.ui.entity

import by.itechart.android.R
import by.itechart.android.data.entity.Section

sealed class LevelItem {
    abstract val viewType: Int

    companion object {
        const val TYPE_HEADER = R.layout.item_level_header
        const val TYPE_SECTION_SINGLE = R.layout.item_level_section_single
        const val TYPE_SECTION_DOUBLE = R.layout.item_level_section
        const val TYPE_BUTTON = R.layout.item_level_button
    }
}

class LevelSectionItem(
    val title: String,
    val topicCount: Int,
    val starCount: Int,
    val color: String,
    override val viewType: Int
) : LevelItem() {

    constructor(topic: Section, color: String, viewType: Int = TYPE_SECTION_DOUBLE) : this(topic.title, topic.topicCount, topic.starCount, color, viewType)

    val isStarted
        get() = starCount != 0
}

class LevelHeaderItem(val title: String) : LevelItem() {
    override val viewType: Int = TYPE_HEADER
}

class LevelButtonItem(val passRate: Int) : LevelItem() {
    override val viewType: Int = TYPE_BUTTON
    val isPassed
        get() = passRate != 0
}