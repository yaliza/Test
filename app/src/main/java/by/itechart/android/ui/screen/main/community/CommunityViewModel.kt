package by.itechart.android.ui.screen.main.community

import androidx.lifecycle.MutableLiveData
import by.itechart.android.ui.base.BaseViewModel
import by.itechart.android.ui.entity.DialogUIModel


class CommunityViewModel : BaseViewModel() {

    val dialog = MutableLiveData<DialogUIModel?>()

    fun setupDialog(dialogUIModel: DialogUIModel?) {
        dialog.value = dialogUIModel
    }

}