package by.itechart.android.ui.screen.main.profile

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import by.itechart.android.data.entity.User
import by.itechart.android.data.repository.Repository
import by.itechart.android.ui.base.BaseViewModel
import by.itechart.android.ui.entity.*
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
        repository.getCertificates()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { cards -> certificates.value = cards }
            .addToDisposables()

        repository.getScores()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { cards -> scores.value = cards }
            .addToDisposables()

        repository.getFollowers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { persons -> followers.value = persons }
            .addToDisposables()

        repository.getFollowing()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { persons -> following.value = persons }
            .addToDisposables()

        repository.getInvitations()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { items -> invitations.value = items }
            .addToDisposables()

        sociableLiveData.addSource(following) { sociableLiveData.value = it }
    }

    fun observeFollowers() {
        sociableLiveData.removeSource(following)
        sociableLiveData.removeSource(invitations)
        sociableLiveData.addSource(followers) { sociableLiveData.value = it }
    }

    fun observeFollowing() {
        sociableLiveData.removeSource(followers)
        sociableLiveData.removeSource(invitations)
        sociableLiveData.addSource(following) { sociableLiveData.value = it }
    }

    fun observeInvitations() {
        sociableLiveData.removeSource(following)
        sociableLiveData.removeSource(followers)
        sociableLiveData.addSource(invitations) { sociableLiveData.value = it }
    }

}