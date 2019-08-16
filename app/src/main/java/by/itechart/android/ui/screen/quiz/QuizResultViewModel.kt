package by.itechart.android.ui.screen.quiz

import androidx.lifecycle.MutableLiveData
import by.itechart.android.data.repository.Repository
import by.itechart.android.ui.base.BaseViewModel
import by.itechart.android.ui.entity.CertificateUIModel
import by.itechart.android.ui.entity.QuizResultUIModel
import by.itechart.android.ui.mapper.CertificateMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class QuizResultViewModel(
    private val repository: Repository,
    private val certificateMapper: CertificateMapper
) : BaseViewModel() {

    val result = MutableLiveData<QuizResultUIModel>()
    val certificate = MutableLiveData<CertificateUIModel>()

    var quizEndingUIModel: QuizResultUIModel? = null
        set(value) {
            if (value != field) {
                result.value = value
                field = value
                loadCertificate()
            }
        }

    private fun loadCertificate() =
        quizEndingUIModel?.let {
            repository.getCertificate(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { item -> certificate.value = certificateMapper.map(item) }
                .addToDisposables()
        }

}