package by.itechart.android.ui.screen.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import by.itechart.android.data.entity.User
import by.itechart.android.data.repository.Repository
import com.facebook.AccessToken
import io.reactivex.schedulers.Schedulers

class LoginViewModel(private val repository: Repository) : ViewModel() {

    val profile: LiveData<User?> = LiveDataReactiveStreams.fromPublisher(
        repository.getProfile().subscribeOn(Schedulers.io())
    )

    fun setFacebookAccessToken(accessToken: AccessToken?) = repository.setFacebookAccessToken(accessToken)
}