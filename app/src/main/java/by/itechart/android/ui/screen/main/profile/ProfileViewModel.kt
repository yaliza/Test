package by.itechart.android.ui.screen.main.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.itechart.android.data.entity.User
import by.itechart.android.data.repository.Repository


class ProfileViewModel(repository: Repository) : ViewModel() {

    val profile: MutableLiveData<User> = MutableLiveData<User>().apply { value = repository.user }

}