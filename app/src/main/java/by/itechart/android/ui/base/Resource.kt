package by.itechart.android.ui.base


sealed class Resource<T>(val data: T? = null, val exception: Throwable? = null) {

    class Success<T>(data: T) : Resource<T>(data)

    class Loading<T> : Resource<T>(null)

    class Error<T>(exception: Throwable) : Resource<T>(null, exception) {
        init {
            exception.printStackTrace()
        }
    }

}