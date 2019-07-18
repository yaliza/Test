package by.itechart.android.ui.screen.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.ActivityNavigator
import by.itechart.android.R
import by.itechart.android.data.entity.User
import by.itechart.android.ui.screen.MainActivity
import com.facebook.AccessToken
import com.facebook.AccessTokenTracker
import com.facebook.CallbackManager
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : AppCompatActivity(R.layout.activity_login) {

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
        loginButton.setPermissions(mutableListOf("public_profile", "email"))
        viewModel.profile.observe(this, Observer { user: User? ->
            user?.let {
                val activityNavigator = ActivityNavigator(this)
                val destination =
                    activityNavigator.createDestination().setIntent(Intent(this, MainActivity::class.java))
                activityNavigator.navigate(destination, null, null, null)
                finish()
            }
        })
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