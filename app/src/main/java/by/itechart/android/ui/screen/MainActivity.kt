package by.itechart.android.ui.screen

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import by.itechart.android.R
import com.facebook.AccessToken
import com.facebook.AccessTokenTracker


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val accessTokenTracker = object : AccessTokenTracker() {
        override fun onCurrentAccessTokenChanged(oldAccessToken: AccessToken?, currentAccessToken: AccessToken?) {
            if (!(currentAccessToken != null && !currentAccessToken.isExpired)) {
                findNavController(R.id.navHostFragment).navigate(R.id.action_toLoginActivity)
                finish()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        accessTokenTracker.startTracking()
    }

    override fun onStop() {
        super.onStop()
        accessTokenTracker.stopTracking()
    }

}
