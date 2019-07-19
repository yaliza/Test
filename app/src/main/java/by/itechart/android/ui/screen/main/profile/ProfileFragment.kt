package by.itechart.android.ui.screen.main.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import by.itechart.android.R
import by.itechart.android.data.entity.User
import by.itechart.android.ext.load
import by.itechart.android.utils.ErrorHandlingObserver
import by.itechart.android.utils.Resource
import kotlinx.android.synthetic.main.fragment_person.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment(R.layout.fragment_person) {

    private val viewModel by viewModel<ProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.profile.observe(this, object : ErrorHandlingObserver<User?>() {
            override fun onSuccess(data: User?) {
                data?.apply {
                    photoUrl?.let { userAvatarImageView.load(photoUrl) }
                    userNameTextView.text = name
                }
            }

            override fun onException(error: Resource.Error<User?>) {}
            override fun onLoading(loading: User?) {}
        })
    }
}