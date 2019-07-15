package by.itechart.android.data.repositories

import by.itechart.android.data.NetworkCallback
import by.itechart.android.data.api.FacebookService
import by.itechart.android.data.entities.User
import com.facebook.AccessToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FacebookRepositoryRetroImpl : FacebookRepository {
    private val facebookService = FacebookService.Factory.create()
    override fun getCurrentUserInfo(callback: NetworkCallback<User>) {
        facebookService.getProfile(AccessToken.getCurrentAccessToken().token).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                response.body()?.let { callback.onComplete(it) } ?: callback.onError("Error while getting profile info")
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                t.message?.let { callback.onError(it) } ?: callback.onError("Error while getting profile info")
            }
        })
    }
}