package by.itechart.android.ui.screen.modal

import androidx.lifecycle.MutableLiveData
import by.itechart.android.data.repository.Repository
import by.itechart.android.ui.base.BaseViewModel
import by.itechart.android.ui.base.Resource
import by.itechart.android.ui.entity.ModalCardUIModel
import by.itechart.android.ui.mapper.ModalCardMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ModalViewModel(
    repository: Repository,
    modalCardMapper: ModalCardMapper
) : BaseViewModel() {

    val modalCards = MutableLiveData<Resource<List<ModalCardUIModel>>>()

    init {
        repository.getModalCards()
            .doOnSubscribe { modalCards.postValue(Resource.Loading()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { cards -> modalCards.value = Resource.Success(modalCardMapper.map(cards)) },
                { error -> modalCards.value = Resource.Error(error) }
            )
            .addToDisposables()
    }

}