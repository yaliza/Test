package by.itechart.android.ui.mapper

import android.content.Context
import by.itechart.android.R
import by.itechart.android.data.entity.Level
import by.itechart.android.ui.entity.LevelButtonUIModel
import by.itechart.android.ui.entity.LevelHeaderUIModel
import by.itechart.android.ui.entity.LevelUIModel
import by.itechart.android.ui.entity.LevelSectionUIModel


class LevelMapper(private val context: Context) {

    fun map(levels: List<Level>) = mutableListOf<LevelUIModel>().apply {
        levels.forEach { level: Level ->
            add(LevelHeaderUIModel(level.title))
            var startIndex = 0
            if (level.sections.isNotEmpty() && level.sections.size % 2 != 0) {
                startIndex = 1
                add(
                    LevelSectionUIModel(
                        level.sections[0].title,
                        context.getString(R.string.level_topics, level.sections[0].topicCount),
                        level.sections[0].starCount,
                        mapColor(level.color),
                        LevelUIModel.TYPE_SECTION_WIDE
                    )
                )
            }
            for (i in startIndex until level.sections.size) {
                add(
                    LevelSectionUIModel(
                        level.sections[i].title,
                        context.getString(R.string.level_topics, level.sections[i].topicCount),
                        level.sections[i].starCount,
                        mapColor(level.color)
                    )
                )
            }

            if (level.passRate != 0) {
                add(LevelButtonUIModel(context.getString(R.string.level_passed, level.passRate), true))
            } else {
                add(LevelButtonUIModel(context.getString(R.string.level_not_passed), false))
            }

        }
    }

    private fun mapColor(color: String) =
        when (color) {
            "green" -> R.color.green20
            "blue" -> R.color.blue10
            else -> R.color.grey50
        }

}