package by.itechart.android.ui.entity


data class DialogUIModel(
    val dialogType: DialogType,
    val message: String,
    val title: String? = null,
    val acceptText: String? = null,
    val declineText: String? = null,
    val icon: Int? = null
)