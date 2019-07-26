package by.itechart.android.ui.mapper

import by.itechart.android.data.entity.Level
import by.itechart.android.ui.entity.LevelButtonItem
import by.itechart.android.ui.entity.LevelHeaderItem
import by.itechart.android.ui.entity.LevelItem
import by.itechart.android.ui.entity.LevelSectionItem


object LevelMapper {

    fun mapToUiModel(levels: List<Level>) =
            mutableListOf<LevelItem>().apply {
                levels.forEach { level: Level ->
                    add(LevelHeaderItem(level.title))
                    var startIndex = 0
                    if (level.sections.isNotEmpty() && level.sections.size % 2 != 0) {
                        startIndex = 1
                        add(
                                LevelSectionItem(
                                        level.sections[0],
                                        level.color,
                                        LevelItem.TYPE_SECTION_SINGLE
                                )
                        )
                    }
                    for (i in startIndex until level.sections.size) {
                        add(LevelSectionItem(level.sections[i], level.color))
                    }
                    add(LevelButtonItem(level.passRate))
                }
            }

}