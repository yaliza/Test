package by.itechart.android.data.repositories

import by.itechart.android.data.entities.User
import by.itechart.android.data.NetworkCallback

interface FacebookRepository {
    fun getCurrentUserInfo(callback: NetworkCallback<User>)
}