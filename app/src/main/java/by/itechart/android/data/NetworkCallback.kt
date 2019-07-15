package by.itechart.android.data

interface NetworkCallback<T> {
    fun onError(msg: String)
    fun onComplete(result: T)
}