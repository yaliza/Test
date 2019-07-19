package by.itechart.android.ui.screen.main.learning

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.itechart.android.R
import by.itechart.android.di.dataModule
import by.itechart.android.ext.navigateTo
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import kotlinx.android.synthetic.main.fragment_school.*
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules


class LearningFragment : Fragment(R.layout.fragment_school) {
    private val googleSignInClient by inject<GoogleSignInClient>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btLogout.setOnClickListener { logout() }
        btSwipable.setOnClickListener { navigateTo(R.id.action_bottomNavFragment_to_swipableFragment) }
    }

    private fun logout() {
        LoginManager.getInstance().logOut()
        googleSignInClient.signOut()
        unloadKoinModules(dataModule)
        loadKoinModules(dataModule)
        navigateTo(R.id.action_toLoginFragment)
    }

}