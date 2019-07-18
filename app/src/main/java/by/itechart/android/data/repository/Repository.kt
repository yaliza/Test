package by.itechart.android.data.repository

import by.itechart.android.data.api.FacebookApi
import by.itechart.android.data.entity.User
import by.itechart.android.data.mock.ModalCards
import by.itechart.android.ui.entity.ModalCardItem
import com.facebook.AccessToken
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class Repository(private val facebookApi: FacebookApi) {

    private val modalCardsSubj: BehaviorSubject<List<ModalCardItem>> = BehaviorSubject.create()
    private val profileSubj: PublishSubject<User> = PublishSubject.create()

    init {
        modalCardsSubj.onNext(ModalCards.mock)
    }

    fun getModalCards(): Flowable<List<ModalCardItem>> = modalCardsSubj.hide().toFlowable(BackpressureStrategy.LATEST)
    fun getProfile(): Flowable<User> = profileSubj.hide().toFlowable(BackpressureStrategy.LATEST)

    fun setAccessToken(accessToken: AccessToken?) {
        if (accessToken?.isExpired == false) {
            facebookApi.getProfile(accessToken.token)
                .subscribeOn(Schedulers.io())
                .subscribe({ user -> profileSubj.onNext(user.body()!!) }, { error -> error.printStackTrace() })
        }
    }
}