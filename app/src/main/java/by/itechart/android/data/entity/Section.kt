package by.itechart.android.data.entity

import com.google.gson.annotations.SerializedName


class Section(
    var id: String = "",
    val title: String = "",
    @SerializedName("topic_count") val topicCount: Int = 0,
    @SerializedName("star_count") val starCount: Int = 0
)
