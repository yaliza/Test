package by.itechart.android.data.mock

import by.itechart.android.R
import by.itechart.android.ui.entity.CertificateUIModel


object Certificates {

    val mock = listOf(
        CertificateUIModel("Level 2 passed", "Julian", "01.01.2018", R.color.green20),
        CertificateUIModel("Level 3 passed", "Marian", "15.02.2018", R.color.blue90),
        CertificateUIModel("Level 4 passed", "Jack", "16.03.2019", R.color.green20)
    )

}