package by.itechart.android.ui.screen.main.events

import androidx.lifecycle.MutableLiveData
import by.itechart.android.data.repository.Repository
import by.itechart.android.ui.base.BaseViewModel
import by.itechart.android.ui.entity.DialogUIModel


class EventsViewModel(
    private val repository: Repository
) : BaseViewModel() {
    val dialog = MutableLiveData<DialogUIModel?>()

    fun setupDialog(dialogUIModel: DialogUIModel?) {
        dialog.value = dialogUIModel
    }
}