package by.itechart.android.data.repositories

import androidx.core.os.bundleOf
import by.itechart.android.data.NetworkCallback
import by.itechart.android.data.entities.User
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.GraphResponse
import com.facebook.HttpMethod

class FacebookRepositoryImpl : FacebookRepository {
    override fun getCurrentUserInfo(callback: NetworkCallback<User>) {
        GraphRequest(AccessToken.getCurrentAccessToken(), "/me/", bundleOf("fields" to "id,name,email"), HttpMethod.GET,
            GraphRequest.Callback { graphResponse: GraphResponse ->
                if (graphResponse.error == null) {
                    val user = User(
                        graphResponse.jsonObject["id"].toString().toLong(),
                        graphResponse.jsonObject["name"].toString(),
                        graphResponse.jsonObject["email"].toString()
                    )
                    callback.onComplete(user)
                } else {
                    callback.onError(graphResponse.error.errorMessage)
                }
            }
        ).executeAsync()
    }
}
