package by.itechart.android.utils

import androidx.lifecycle.Observer


sealed class Resource<T>(
    val data: T? = null,
    val exception: Throwable? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(exception: Throwable?, data: T? = null) : Resource<T>(data, exception)
}

abstract class ErrorHandlingObserver<T> : Observer<Resource<T>> {

    override fun onChanged(t: Resource<T>?) {
        when (t) {
            is Resource.Success -> onSuccess(t.data)
            is Resource.Error -> onException(t)
            is Resource.Loading -> onLoading(t.data)
        }
    }

    abstract fun onSuccess(data: T?)
    abstract fun onException(error: Resource.Error<T>)
    abstract fun onLoading(loading: T?)

}