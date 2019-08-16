package by.itechart.android.ui.entity

import by.itechart.android.R

sealed class LevelUIModel {
    abstract val viewType: Int
    abstract val levelId: String

    companion object {
        const val TYPE_HEADER = R.layout.item_level_header
        const val TYPE_SECTION_WIDE = R.layout.item_level_section_wide
        const val TYPE_SECTION = R.layout.item_level_section
        const val TYPE_BUTTON = R.layout.item_level_button
    }
}

data class LevelSectionUIModel(
    override val levelId: String,
    val sectionId: String,
    val title: String,
    val topicTitle: String,
    val starCount: Int,
    val color: Int,
    override val viewType: Int = TYPE_SECTION
) : LevelUIModel()

data class LevelHeaderUIModel(
    override val levelId: String,
    val title: String
) : LevelUIModel() {
    override val viewType: Int = TYPE_HEADER
}

data class LevelButtonUIModel(
    override val levelId: String,
    val title: String, val isPassed: Boolean
) : LevelUIModel() {
    override val viewType: Int = TYPE_BUTTON
}