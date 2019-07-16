package by.itechart.android.data.repositories

import by.itechart.android.data.api.FacebookService
import by.itechart.android.data.entities.User
import com.facebook.AccessToken
import io.reactivex.Single

class FacebookRepositoryRetroImpl : FacebookRepository {
    private val facebookService = FacebookService.Factory.create()
    override fun getCurrentUserInfo(): Single<User> {
        return facebookService
            .getProfile(AccessToken.getCurrentAccessToken().token)
            .map { it.body() }
    }
}