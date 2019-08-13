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

data class LevelSectionUIModel(
    val title: String,
    val topicTitle: String,
    val starCount: Int,
    val color: Int,
    override val viewType: Int = TYPE_SECTION
) : LevelUIModel()

data class LevelHeaderUIModel(val title: String) : LevelUIModel() {
    override val viewType: Int = TYPE_HEADER
}

data class LevelButtonUIModel(val title : String, val isPassed : Boolean) : LevelUIModel() {
    override val viewType: Int = TYPE_BUTTON
}