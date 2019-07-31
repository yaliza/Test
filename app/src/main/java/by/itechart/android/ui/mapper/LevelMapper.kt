package by.itechart.android.ui.mapper

import by.itechart.android.data.entity.Level
import by.itechart.android.ui.entity.LevelButtonUIModel
import by.itechart.android.ui.entity.LevelHeaderUIModel
import by.itechart.android.ui.entity.LevelUIModel
import by.itechart.android.ui.entity.LevelSectionUIModel


object LevelMapper {

    fun map(levels: List<Level>) = mutableListOf<LevelUIModel>().apply {
        levels.forEach { level: Level ->
            add(LevelHeaderUIModel(level.title))
            var startIndex = 0
            if (level.sections.isNotEmpty() && level.sections.size % 2 != 0) {
                startIndex = 1
                add(
                    LevelSectionUIModel(
                        level.sections[0],
                        level.color,
                        LevelUIModel.TYPE_SECTION_WIDE
                    )
                )
            }
            for (i in startIndex until level.sections.size) {
                add(LevelSectionUIModel(level.sections[i], level.color))
            }
            add(LevelButtonUIModel(level.passRate))
        }
    }

}