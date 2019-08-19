package by.itechart.android.data.entity

import com.google.gson.annotations.SerializedName

class Level(
    var id: String = "",
    var title: String = "",
    var color: String = "",
    var level: Int = 0,
    @SerializedName("pass_rate") var passRate: Int = 0,
    @SerializedName("sections") var sections: MutableList<Section> = mutableListOf()
)