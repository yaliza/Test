package by.itechart.android.data.repositories

import androidx.core.os.bundleOf
import by.itechart.android.data.entities.User
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.HttpMethod
import io.reactivex.Single

class FacebookRepositoryImpl : FacebookRepository {

    override fun getCurrentUserInfo(): Single<User> {
        val graphRequest = GraphRequest(
            AccessToken.getCurrentAccessToken(),
            "/me/",
            bundleOf("fields" to "id,name,email"),
            HttpMethod.GET
        )

        return Single.fromCallable<User> {
            val graphResponse = graphRequest.executeAndWait()
            return@fromCallable User(
                graphResponse.jsonObject["id"].toString().toLong(),
                graphResponse.jsonObject["name"].toString(),
                graphResponse.jsonObject["email"].toString()
            )
        }
    }
}
