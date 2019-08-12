package by.itechart.android.data.entity

import com.google.gson.annotations.SerializedName

class Level(
    var title: String = "",
    var color: String = "",
    var level: Int = 0,
    var passRate: Int = 0,
    @SerializedName("items") var sections: MutableList<Section> = mutableListOf()
)

class Section(
    val title: String = "",
    val topicCount: Int = 0,
    val starCount: Int = 0
)
