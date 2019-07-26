package by.itechart.android.ui.screen.main.community

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.itechart.android.R
import by.itechart.android.di.dataModule
import by.itechart.android.ext.navigate
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import kotlinx.android.synthetic.main.fragment_community.*
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules


class CommunityFragment : Fragment(R.layout.fragment_community) {

    private val googleSignInClient by inject<GoogleSignInClient>()
    private val facebookLoginManager by inject<LoginManager>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logoutButton.setOnClickListener { logout() }
        goToModularButton.setOnClickListener { navigate(R.id.action_bottomNavFragment_to_swipableFragment) }
    }

    private fun logout() {
        facebookLoginManager.logOut()
        googleSignInClient.signOut()
        unloadKoinModules(dataModule)
        loadKoinModules(dataModule)
        navigate(R.id.action_toLoginFragment)
    }
}