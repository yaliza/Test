package by.itechart.android.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


open class BaseViewModel : ViewModel() {

    private val disposables = CompositeDisposable()

    protected fun Disposable.addToDisposables() = disposables.add(this)

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}