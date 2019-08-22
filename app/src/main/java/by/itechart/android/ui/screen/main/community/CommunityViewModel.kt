package by.itechart.android.ui.screen.main.community

import androidx.lifecycle.MutableLiveData
import by.itechart.android.data.entity.Activity
import by.itechart.android.data.entity.Stat
import by.itechart.android.data.entity.User
import by.itechart.android.data.repository.Repository
import by.itechart.android.ui.base.BaseViewModel
import by.itechart.android.ui.base.Resource
import by.itechart.android.ui.entity.LevelStatUIModel
import by.itechart.android.ui.mapper.LevelStatMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class CommunityViewModel(
    private val repository: Repository,
    private val levelStatMapper: LevelStatMapper
) : BaseViewModel() {

    val profile = MutableLiveData<User>().apply { value = repository.user }
    val stats = MutableLiveData<Resource<List<Stat>>>()
    val levelStats = MutableLiveData<Resource<List<LevelStatUIModel>>>()
    val activities = MutableLiveData<Resource<List<Activity>>>()

    init {
        repository.getStats()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { items -> stats.value = Resource.Success(items) },
                { error -> stats.value = Resource.Error(error) })
            .addToDisposables()

        repository.getLevelStats()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { items -> levelStats.value = Resource.Success(levelStatMapper.map(items)) },
                { error -> levelStats.value = Resource.Error(error) })
            .addToDisposables()

        repository.getActivities()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { items -> activities.value = Resource.Success(items) },
                { error -> activities.value = Resource.Error(error) })
            .addToDisposables()
    }
}