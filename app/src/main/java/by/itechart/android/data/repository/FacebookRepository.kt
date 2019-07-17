package by.itechart.android.data.repository

import by.itechart.android.data.entity.User
import io.reactivex.Single

interface FacebookRepository {
    fun getCurrentUserInfo() : Single<User>
}