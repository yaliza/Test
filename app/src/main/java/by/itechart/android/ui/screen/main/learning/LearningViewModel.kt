package by.itechart.android.ui.screen.main.learning

import androidx.lifecycle.MutableLiveData
import by.itechart.android.data.entity.Level
import by.itechart.android.data.repository.Repository
import by.itechart.android.ui.base.BaseViewModel
import by.itechart.android.ui.base.Resource
import by.itechart.android.ui.entity.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class LearningViewModel(repository: Repository) : BaseViewModel() {

    val levelCards = MutableLiveData<Resource<List<LevelCardItem>>>()

    init {
        repository.getLevels()
                .doOnSubscribe { levelCards.postValue(Resource.Loading()) }
                .map { levels: List<Level> -> levels.toLevelCardItems() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { cards -> levelCards.value = Resource.Success(cards) },
                        { error -> levelCards.value = Resource.Error(error) }
                )
                .addToDisposables()
    }

    private fun List<Level>.toLevelCardItems(): List<LevelCardItem> {
        val result = mutableListOf<LevelCardItem>()
        forEach { level: Level ->
            result.add(LevelHeaderItem(level.title))
            var startIndex = 0
            if (level.sections.isNotEmpty() && level.sections.size % 2 != 0) {
                startIndex = 1
                result.add(SectionSingleItem(level.sections[0], level.color))
            }
            for (i in startIndex until level.sections.size) {
                result.add(SectionDoubleItem(level.sections[i], level.color))
            }
            result.add(LevelButtonItem(level.passRate))
        }
        return result
    }
}