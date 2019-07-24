package by.itechart.android.ui.entity

import by.itechart.android.data.entity.Section


sealed class LevelCardItem

sealed class LevelSectionItem(
    val title: String,
    val topicCount: Int,
    val starCount: Int,
    val color: String
) : LevelCardItem() {
    val isStarted
        get() = starCount != 0
}

class SectionSingleItem(
    title: String,
    topicCount: Int,
    starCount: Int,
    color: String
) : LevelSectionItem(title, topicCount, starCount, color) {
    constructor(topic: Section, color: String) : this(topic.title, topic.topicCount, topic.starCount, color)
}

class SectionDoubleItem(
    title: String,
    topicCount: Int,
    starCount: Int,
    color: String
) : LevelSectionItem(title, topicCount, starCount, color) {
    constructor(topic: Section, color: String) : this(topic.title, topic.topicCount, topic.starCount, color)
}

class LevelHeaderItem(val title: String) : LevelCardItem()

class LevelButtonItem(val passRate: Int) : LevelCardItem() {
    val isPassed
        get() = passRate != 0
}