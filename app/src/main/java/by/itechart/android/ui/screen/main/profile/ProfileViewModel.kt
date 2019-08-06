package by.itechart.android.ui.screen.main.profile

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import by.itechart.android.data.entity.User
import by.itechart.android.data.repository.Repository
import by.itechart.android.ui.base.BaseViewModel
import by.itechart.android.ui.entity.*
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ProfileViewModel(repository: Repository) : BaseViewModel() {

    private val invitations = MutableLiveData<List<InvitationUiModel>>()
    val profile = MutableLiveData<User>().apply { value = repository.user }
    val certificates = MutableLiveData<List<CertificateUIModel>>()
    val scores = MutableLiveData<List<ScoreUIModel>>()
    val followers = MutableLiveData<List<UserUIModel>>()
    val following = MutableLiveData<List<UserUIModel>>()
    val sociableLiveData = MediatorLiveData<List<SociableUIModel>>()

    init {
        with(repository) {
            getCertificates().observe { cards -> certificates.value = cards }
            getScores().observe { cards -> scores.value = cards }
            getFollowers().observe { persons -> followers.value = persons }
            getFollowing().observe { persons -> following.value = persons }
            getInvitations().observe { items -> invitations.value = items }
        }
        sociableLiveData.addSource(following) { sociableLiveData.value = it }
    }

    fun observeFollowers() =
        with(sociableLiveData) {
            removeSource(following)
            removeSource(invitations)
            addSource(followers) { sociableLiveData.value = it }
        }

    fun observeFollowing() =
        with(sociableLiveData) {
            removeSource(followers)
            removeSource(invitations)
            addSource(following) { sociableLiveData.value = it }
        }

    fun observeInvitations() =
        with(sociableLiveData) {
            removeSource(following)
            removeSource(followers)
            addSource(invitations) { sociableLiveData.value = it }
        }

    private fun <T> Flowable<T>.observe(func: (T) -> Unit) =
        subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(func)
            .addToDisposables()
}