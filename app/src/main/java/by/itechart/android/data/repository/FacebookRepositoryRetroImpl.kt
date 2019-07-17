package by.itechart.android.data.repository

import by.itechart.android.data.api.FacebookApi
import by.itechart.android.data.entity.User
import com.facebook.AccessToken
import io.reactivex.Single

class FacebookRepositoryRetroImpl : FacebookRepository {
    private val facebookApi = FacebookApi.Factory.create()
    override fun getCurrentUserInfo(): Single<User> {
        return facebookApi
            .getProfile(AccessToken.getCurrentAccessToken().token)
            .map { it.body() }
    }
}