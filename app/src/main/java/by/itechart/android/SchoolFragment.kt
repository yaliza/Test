package by.itechart.android

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import by.itechart.android.data.NetworkCallback
import by.itechart.android.data.entities.User
import by.itechart.android.data.repositories.FacebookRepositoryImpl
import by.itechart.android.data.repositories.FacebookRepositoryRetroImpl
import com.facebook.login.LoginManager
import kotlinx.android.synthetic.main.fragment_school.*

class SchoolFragment : Fragment(R.layout.fragment_school) {

    private val userCallback = object : NetworkCallback<User> {
        override fun onError(msg: String) = Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()

        override fun onComplete(result: User) {
            if(schoolLabel != null) schoolLabel.text = "${result.name} ${result.email}"
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btLogout.setOnClickListener { LoginManager.getInstance().logOut() }
        btSwipable.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                .navigate(R.id.action_bottomNavFragment_to_swipableFragment)
        }

        FacebookRepositoryRetroImpl().apply { getCurrentUserInfo(userCallback) }
    }
}