package by.itechart.android.ui.entity


class DialogUIModel(
    val dialogType: DialogType,
    val message: String,
    val title: String? = null,
    val acceptText: String? = null,
    val declineText: String? = null,
    val icon: Int? = null
)

enum class DialogType { ACCEPT, INFO, BOTTOM }