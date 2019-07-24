package by.itechart.android.data.entity

import com.google.gson.annotations.SerializedName


class LevelsResponse(
    @SerializedName("items") val levels: List<Level>
)

class Level(
    val title: String,
    val color: String,
    val passRate: Int,
    @SerializedName("items") val sections: List<Section>
)

class Section(
    val title: String,
    val topicCount: Int,
    val starCount: Int
)
