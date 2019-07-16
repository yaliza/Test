package by.itechart.android.data.repositories

import by.itechart.android.data.entities.User
import io.reactivex.Single

interface FacebookRepository {
    fun getCurrentUserInfo() : Single<User>
}