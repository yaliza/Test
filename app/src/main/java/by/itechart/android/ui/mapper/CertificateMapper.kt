package by.itechart.android.ui.mapper

import by.itechart.android.R
import by.itechart.android.data.entity.Certificate
import by.itechart.android.ui.entity.CertificateUIModel


class CertificateMapper {

    fun map(certificates: List<Certificate>) =
        mutableListOf<CertificateUIModel>().apply {
            certificates.forEach { add(CertificateUIModel(it.title, it.name, it.date, mapColor(it.color))) }
        }

    fun map(certificate: Certificate) = with(certificate) { CertificateUIModel(title, name, date, mapColor(color)) }

    private fun mapColor(color: String) =
        when (color) {
            "green" -> R.color.green20
            "orange" -> R.color.orange50
            else -> R.color.blue90
        }
}