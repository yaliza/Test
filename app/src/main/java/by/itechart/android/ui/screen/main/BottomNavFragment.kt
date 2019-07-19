package by.itechart.android.ui.screen.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import by.itechart.android.R
import com.facebook.AccessToken
import com.facebook.AccessTokenTracker
import kotlinx.android.synthetic.main.fragment_bottom_nav.*


class BottomNavFragment : Fragment(R.layout.fragment_bottom_nav) {

    private val accessTokenTracker = object : AccessTokenTracker() {
        override fun onCurrentAccessTokenChanged(oldAccessToken: AccessToken?, currentAccessToken: AccessToken?) {
            if (currentAccessToken == null || currentAccessToken.isExpired) {
                activity?.let { act: FragmentActivity ->
                    Navigation.findNavController(act, R.id.navHostFragment).navigate(R.id.action_toLoginFragment)
                }
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