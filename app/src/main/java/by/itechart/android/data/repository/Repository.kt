package by.itechart.android.data.repository

import by.itechart.android.data.api.FacebookApi
import by.itechart.android.data.entity.User
import by.itechart.android.data.helper.UserHelper
import by.itechart.android.data.mock.ModalCards
import by.itechart.android.ui.entity.ModalCardItem
import by.itechart.android.utils.Resource
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class Repository(
    private val facebookApi: FacebookApi,
    private val userHelper: UserHelper
) {

    private val modalCardsSubj: BehaviorSubject<List<ModalCardItem>> = BehaviorSubject.create()
    private val profileSubj: BehaviorSubject<Resource<User?>> = BehaviorSubject.create()

    init {
        modalCardsSubj.onNext(ModalCards.mock)
    }

    fun getModalCards(): Flowable<List<ModalCardItem>> = modalCardsSubj.hide().toFlowable(BackpressureStrategy.LATEST)
    fun getProfile(): Flowable<Resource<User?>> = profileSubj.hide().toFlowable(BackpressureStrategy.LATEST)

    fun setFacebookAccessToken(accessToken: AccessToken?) {
        if (accessToken != null && !accessToken.isExpired) {
            facebookApi.getProfile(accessToken.token)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { user ->
                        user.body()?.toUser()?.let { user: User ->
                            userHelper.user = user
                            profileSubj.onNext(Resource.Success(user))
                        }
                    },
                    { error -> profileSubj.onNext(Resource.Error(error)) })
        }
    }

    fun setGoogleSignInAccount(task: Task<GoogleSignInAccount>) {
        try {
            task.getResult(ApiException::class.java)
                ?.let { account: GoogleSignInAccount -> setGoogleSignInAccount(account) }
        } catch (apiException: ApiException) {
            if(apiException.statusCode != GoogleSignInStatusCodes.SIGN_IN_CANCELLED) {
                profileSubj.onNext(Resource.Error(apiException))
            }
        }
    }

    fun setGoogleSignInAccount(account: GoogleSignInAccount) {
        val user = User(account.id ?: "", account.displayName ?: "", account.email ?: "", account.photoUrl.toString())
        userHelper.user = user
        profileSubj.onNext(Resource.Success(user))
    }
}