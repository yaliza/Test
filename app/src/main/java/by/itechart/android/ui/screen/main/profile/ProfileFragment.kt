package by.itechart.android.ui.screen.main.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import by.itechart.android.R
import by.itechart.android.data.entity.User
import by.itechart.android.ext.loadCircle
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val viewModel by viewModel<ProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.profile.observe(this, Observer { updateUserInfo(it) })
    }

    private fun updateUserInfo(user: User) = with(user) {
        userNameTextView.text = name
        photoUrl?.let { userAvatarImageView.loadCircle(photoUrl) }
    }

}