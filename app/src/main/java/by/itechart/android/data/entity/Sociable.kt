package by.itechart.android.data.entity


enum class SociableType {
    FOLLOWING, FOLLOWER, INVITE
}

class Sociable(
    val name: String = "",
    val avatar: String = "",
    var type: SociableType = SociableType.FOLLOWER
)