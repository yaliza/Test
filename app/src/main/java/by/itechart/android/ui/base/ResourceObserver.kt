package by.itechart.android.ui.base

import androidx.lifecycle.Observer


abstract class ResourceObserver<T> : Observer<Resource<T>> {

    override fun onChanged(t: Resource<T>) {
        when (t) {
            is Resource.Success -> onSuccess(t.data)
            is Resource.Loading -> onLoading()
            is Resource.Error -> onError(t.exception?.message ?: "Error")
        }
    }

    abstract fun onSuccess(data: T?): Unit?
    abstract fun onLoading()
    abstract fun onError(message: String)

}