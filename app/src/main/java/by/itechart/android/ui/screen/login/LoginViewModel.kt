package by.itechart.android.ui.screen.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import by.itechart.android.data.entity.User
import by.itechart.android.data.repository.Repository
import by.itechart.android.utils.Resource
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import io.reactivex.schedulers.Schedulers

class LoginViewModel(private val repository: Repository) : ViewModel() {

    val profile: LiveData<Resource<User?>> = LiveDataReactiveStreams.fromPublisher(
        repository.getProfile().subscribeOn(Schedulers.io())
    )

    fun setFacebookAccessToken(accessToken: AccessToken?) = repository.setFacebookAccessToken(accessToken)
    fun setGoogleAccount(task: Task<GoogleSignInAccount>) = repository.setGoogleSignInAccount(task)
    fun setGoogleAccount(account: GoogleSignInAccount?) {
        account?.let { repository.setGoogleSignInAccount(account) }
    }
}