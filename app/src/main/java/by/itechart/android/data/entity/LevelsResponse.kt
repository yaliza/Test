package by.itechart.android.data.entity

import com.google.gson.annotations.SerializedName


class LevelsResponse(
    @SerializedName("items") val levels: List<Level>
)

data class Level(
    val title: String,
    val color: String,
    val passRate: Int,
    @SerializedName("items") val sections: List<Section>
)

data class Section(
    val title: String,
    val topicCount: Int,
    val starCount: Int
)
