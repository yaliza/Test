package by.itechart.android.ui.screen.main.profile

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import by.itechart.android.data.entity.Score
import by.itechart.android.data.entity.User
import by.itechart.android.data.repository.Repository
import by.itechart.android.ui.base.BaseViewModel
import by.itechart.android.ui.base.Resource
import by.itechart.android.ui.entity.CertificateUIModel
import by.itechart.android.ui.entity.SociableUIModel
import by.itechart.android.ui.mapper.CertificateMapper
import by.itechart.android.ui.mapper.SociableMapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ProfileViewModel(
    private val sociableMapper: SociableMapper,
    private val certificateMapper: CertificateMapper,
    repository: Repository
) : BaseViewModel() {

    private val invitations = MutableLiveData<Resource<List<SociableUIModel>>>()
    val profile = MutableLiveData<User>().apply { value = repository.user }
    val certificates = MutableLiveData<Resource<List<CertificateUIModel>>>()
    val scores = MutableLiveData<Resource<List<Score>>>()
    val followers = MutableLiveData<Resource<List<SociableUIModel>>>()
    val following = MutableLiveData<Resource<List<SociableUIModel>>>()
    val sociableLiveData = MediatorLiveData<Resource<List<SociableUIModel>>>()

    init {
        with(repository) {
            getCertificates().observe(
                { items -> certificates.value = Resource.Success(certificateMapper.map(items)) },
                { error -> certificates.value = Resource.Error(error) }
            )
            getScores().observe(
                { items -> scores.value = Resource.Success(items) },
                { error -> scores.value = Resource.Error(error) }
            )
            getFollowers().observe(
                { persons -> followers.value = Resource.Success(sociableMapper.map(persons)) },
                { error -> followers.value = Resource.Error(error) }
            )
            getFollowing().observe(
                { persons -> following.value = Resource.Success(sociableMapper.map(persons)) },
                { error -> following.value = Resource.Error(error) }
            )
            getInvitations().observe(
                { items -> invitations.value = Resource.Success(sociableMapper.map(items)) },
                { error -> invitations.value = Resource.Error(error) }
            )
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

    private fun <T> Flowable<T>.observe(onNext: (T) -> Unit, onError: (Throwable) -> Unit) =
        subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onNext, onError)
            .addToDisposables()
}