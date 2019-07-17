package by.itechart.android.ui.screen.modal

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import by.itechart.android.data.repository.Repository
import by.itechart.android.ui.entity.ModalCardItem
import io.reactivex.schedulers.Schedulers


class ModalViewModel(repository: Repository) : ViewModel() {

    val modalCards: LiveData<List<ModalCardItem>> = LiveDataReactiveStreams.fromPublisher(
        repository.getModalCards()
            .subscribeOn(Schedulers.io())
    )

}