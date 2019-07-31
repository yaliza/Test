package by.itechart.android.data.repository

import by.itechart.android.data.api.FacebookApi
import by.itechart.android.data.entity.FacebookResponseUser
import by.itechart.android.data.entity.Level
import by.itechart.android.data.entity.User
import by.itechart.android.data.mock.Levels
import by.itechart.android.data.mock.ModalCards
import by.itechart.android.data.mock.Topics
import by.itechart.android.ui.entity.ModalCardUIModel
import by.itechart.android.ui.entity.TopicUIModel
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import retrofit2.Response
import java.util.concurrent.TimeUnit


class Repository(
        private val facebookApi: FacebookApi,
        private val userHelper: UserHelper,
        levels: Levels
) {

    val user: User
        get() = userHelper.user ?: throw IllegalArgumentException("No user detected")

    private val modalCardsSubj: BehaviorSubject<List<ModalCardUIModel>> = BehaviorSubject.create()
    private val topicsSubj: BehaviorSubject<List<TopicUIModel>> = BehaviorSubject.create()
    private val levelsSubj: BehaviorSubject<List<Level>> = BehaviorSubject.create()

    init {
        modalCardsSubj.onNext(ModalCards.mock)
        topicsSubj.onNext(Topics.mock)
        levelsSubj.onNext(levels.getLevels())
    }

    fun getLevels(): Flowable<List<Level>> = levelsSubj.hide()
            .toFlowable(BackpressureStrategy.LATEST)
            .delay(1, TimeUnit.SECONDS)

    fun getModalCards(): Flowable<List<ModalCardUIModel>> = modalCardsSubj.hide()
            .toFlowable(BackpressureStrategy.LATEST)
            .delay(1, TimeUnit.SECONDS)

    fun getTopics(): Flowable<List<TopicUIModel>> = topicsSubj.hide()
            .toFlowable(BackpressureStrategy.LATEST)
            .delay(1, TimeUnit.SECONDS)

    fun getFacebookUser(accessToken: AccessToken): Single<Response<FacebookResponseUser>> =
            facebookApi.getProfile(accessToken.token)
                    .doOnSuccess { response -> response.body()?.let { userHelper.user = User(it) } }
                    .delay(1, TimeUnit.SECONDS)

    fun getGoogleUser(task: Task<GoogleSignInAccount>): Single<GoogleSignInAccount> =
            Single.create<GoogleSignInAccount> { emitter ->
                try {
                    task.getResult(ApiException::class.java)?.let { emitter.onSuccess(it) }
                } catch (apiException: ApiException) {
                    if (apiException.statusCode != GoogleSignInStatusCodes.SIGN_IN_CANCELLED) {
                        emitter.onError(apiException)
                    }
                }
            }.doOnSuccess { userHelper.user = User(it) }

    fun getGoogleUser(user: GoogleSignInAccount): Single<GoogleSignInAccount> =
            Single.just(user)
                    .doOnSuccess { userHelper.user = User(it) }
                    .delay(1, TimeUnit.SECONDS)
}