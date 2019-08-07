package by.itechart.android.ui.dialog


fun dialog(init: DialogBuilder.() -> Unit) =
    with(DialogBuilder()) {
        init()
        build()
    }