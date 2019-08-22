package by.itechart.android.ui.mapper

import android.content.Context
import by.itechart.android.R
import by.itechart.android.data.entity.LevelStat
import by.itechart.android.ui.entity.LevelStatUIModel


class LevelStatMapper(private val context: Context) {

    fun map(levelStats: List<LevelStat>) = mutableListOf<LevelStatUIModel>().apply {
        levelStats.forEach {
            add(LevelStatUIModel(context.getString(R.string.community_level_title, it.level), it.description, it.photos))
        }
    }

}