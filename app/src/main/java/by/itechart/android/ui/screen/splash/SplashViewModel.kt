package by.itechart.android.ui.screen.splash

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import by.itechart.android.data.entity.User
import by.itechart.android.data.repository.Repository
import by.itechart.android.ui.base.BaseViewModel
import by.itechart.android.ui.base.Resource
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class SplashViewModel(private val repository: Repository) : BaseViewModel() {

    val user: MutableLiveData<Resource<User?>> = MutableLiveData()

    fun setAccounts(facebookAccessToken: AccessToken?, googleAccount: GoogleSignInAccount?) {
        if (facebookAccessToken != null && !facebookAccessToken.isExpired) {
            observeFacebookUser(facebookAccessToken)
            return
        }
        if (googleAccount != null && !googleAccount.isExpired) {
            observeGoogleUser(googleAccount)
            return
        }
        Handler().postDelayed({ user.value = Resource.Success(null) }, 1000)
    }

    private fun observeFacebookUser(facebookAccessToken: AccessToken) {
        repository.getFacebookUser(facebookAccessToken)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response -> response.body()?.let { user.value = Resource.Success(User(it)) } },
                { error -> user.value = Resource.Error(error) })
            .addToDisposables()
    }

    private fun observeGoogleUser(googleSignInAccount: GoogleSignInAccount) {
        repository.getGoogleUser(googleSignInAccount)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { googleAccount -> user.value = Resource.Success(User(googleAccount)) },
                { error -> user.value = Resource.Error(error) })
            .addToDisposables()
    }
}