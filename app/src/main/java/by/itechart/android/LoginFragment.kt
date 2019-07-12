package by.itechart.android

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.facebook.CallbackManager
import kotlinx.android.synthetic.main.fragment_login.*
import com.facebook.AccessTokenTracker
import com.facebook.AccessToken

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val accessTokenTracker = object : AccessTokenTracker() {
        override fun onCurrentAccessTokenChanged(oldAccessToken: AccessToken?, currentAccessToken: AccessToken?) {
            if (currentAccessToken != null && !currentAccessToken.isExpired) {
                activity?.findNavController(R.id.navHostFragment)?.navigate(R.id.action_loginFragment_to_bottomNavFragment)
            }
        }
    }

    private val callbackManager = CallbackManager.Factory.create()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val accessToken = AccessToken.getCurrentAccessToken()
        if (accessToken != null && !accessToken.isExpired) {
            findNavController().navigate(R.id.action_loginFragment_to_bottomNavFragment)
        }
        loginButton.fragment = this
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
}