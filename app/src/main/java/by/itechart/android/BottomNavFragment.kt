package by.itechart.android

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.facebook.AccessToken
import com.facebook.AccessTokenTracker
import kotlinx.android.synthetic.main.fragment_bottom_nav.*


class BottomNavFragment : Fragment(R.layout.fragment_bottom_nav) {

    private val accessTokenTracker = object : AccessTokenTracker() {
        override fun onCurrentAccessTokenChanged(oldAccessToken: AccessToken?, currentAccessToken: AccessToken?) {
            if (!(currentAccessToken != null && !currentAccessToken.isExpired)) {
                findNavController().navigate(R.id.action_toLoginFragment)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(requireActivity(), R.id.bottomNavFragment)
        bottomNavView.setupWithNavController(navController)
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