package by.itechart.android.ui.dialog


inline fun dialog(init: DialogBuilder.() -> Unit) =
    with(DialogBuilder()) {
        init()
        build()
    }