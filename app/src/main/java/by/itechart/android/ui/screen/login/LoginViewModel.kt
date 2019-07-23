package by.itechart.android.ui.screen.login

import androidx.lifecycle.MutableLiveData
import by.itechart.android.data.entity.User
import by.itechart.android.data.repository.Repository
import by.itechart.android.ui.base.BaseViewModel
import by.itechart.android.ui.base.Resource
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class LoginViewModel(private val repository: Repository) : BaseViewModel() {

    val user: MutableLiveData<Resource<User?>> = MutableLiveData()

    fun getFacebookUser(accessToken: AccessToken) = repository.getFacebookUser(accessToken)
            .doOnSubscribe { user.postValue(Resource.Loading()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                    { response -> response.body()?.let { user.value = Resource.Success(User(it)) } },
                    { error -> user.value = Resource.Error(error) })
            .addToDisposables()

    fun getGoogleUser(task: Task<GoogleSignInAccount>) = repository.getGoogleUser(task)
            .doOnSubscribe { user.postValue(Resource.Loading()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                    { googleAccount -> user.value = Resource.Success(User(googleAccount)) },
                    { error -> user.value = Resource.Error(error) }
            )
            .addToDisposables()

}