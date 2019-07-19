package by.itechart.android.ui.screen.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import by.itechart.android.data.entity.User
import by.itechart.android.data.repository.Repository
import by.itechart.android.utils.Resource
import io.reactivex.schedulers.Schedulers


class ProfileViewModel(repository: Repository) : ViewModel() {

    val profile: LiveData<Resource<User?>> = LiveDataReactiveStreams.fromPublisher(
        repository.getProfile().subscribeOn(Schedulers.io())
    )

}