package by.itechart.android.ui.screen.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import by.itechart.android.R
import by.itechart.android.data.entity.User
import com.facebook.AccessToken
import com.facebook.AccessTokenTracker
import com.facebook.CallbackManager
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : Fragment(R.layout.fragment_login) {

    val viewModel by viewModel<LoginViewModel>()

    private val accessTokenTracker = object : AccessTokenTracker() {
        override fun onCurrentAccessTokenChanged(oldAccessToken: AccessToken?, currentAccessToken: AccessToken?) {
            viewModel.setFacebookAccessToken(currentAccessToken)
        }
    }

    private val callbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setFacebookAccessToken(AccessToken.getCurrentAccessToken())
        viewModel.profile.observe(this, Observer { goToBottomNavFragment(it) })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        loginButton.setPermissions(mutableListOf("public_profile", "email"))
//        loginButton.fragment = this
    }

    override fun onStart() {
        super.onStart()
        accessTokenTracker.startTracking()
    }

    override fun onStop() {
        super.onStop()
        accessTokenTracker.stopTracking()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun goToBottomNavFragment(user: User?) {
        user?.let {
            activity?.let { act: FragmentActivity ->
                findNavController(act, R.id.navHostFragment).navigate(R.id.action_loginFragment_to_bottomNavFragment)
            }
        }
    }

}