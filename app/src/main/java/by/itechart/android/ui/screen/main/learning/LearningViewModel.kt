package by.itechart.android.ui.screen.main.learning

import androidx.lifecycle.MutableLiveData
import by.itechart.android.data.entity.Level
import by.itechart.android.data.repository.Repository
import by.itechart.android.ui.base.BaseViewModel
import by.itechart.android.ui.base.Resource
import by.itechart.android.ui.entity.*
import by.itechart.android.ui.mapper.LevelMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class LearningViewModel(repository: Repository) : BaseViewModel() {

    val levelCards = MutableLiveData<Resource<List<LevelItem>>>()

    init {
        repository.getLevels()
                .doOnSubscribe { levelCards.postValue(Resource.Loading()) }
                .map { levels: List<Level> -> LevelMapper.mapToUiModel(levels) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { cards -> levelCards.value = Resource.Success(cards) },
                        { error -> levelCards.value = Resource.Error(error) }
                )
                .addToDisposables()
    }

}