package by.itechart.android.ui.screen.module

import androidx.lifecycle.MutableLiveData
import by.itechart.android.data.repository.Repository
import by.itechart.android.ui.base.BaseViewModel
import by.itechart.android.ui.base.Resource
import by.itechart.android.ui.entity.TopicUIModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ModuleViewModel(repository: Repository) : BaseViewModel() {

    val topicItems = MutableLiveData<Resource<List<TopicUIModel>>>()

    init {
        repository.getTopics()
            .doOnSubscribe { topicItems.postValue(Resource.Loading()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { topics -> topicItems.value = Resource.Success(topics) },
                { error -> topicItems.value = Resource.Error(error) }
            )
            .addToDisposables()
    }
}