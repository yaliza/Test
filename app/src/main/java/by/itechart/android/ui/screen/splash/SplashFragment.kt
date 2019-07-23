package by.itechart.android.ui.screen.splash

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.itechart.android.R
import by.itechart.android.data.entity.User
import by.itechart.android.ext.navigate
import by.itechart.android.ui.base.ResourceObserver
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignIn
import org.koin.android.ext.android.inject


class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val viewModel by inject<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setAccounts(
            AccessToken.getCurrentAccessToken(),
            GoogleSignIn.getLastSignedInAccount(activity)
        )

        viewModel.user.observe(this, object : ResourceObserver<User?>() {
            override fun onSuccess(data: User?) = navigateNext(data)
            override fun onLoading() {}
            override fun onError(message: String) {
                Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun navigateNext(user: User?) {
        if (user == null) {
            navigate(R.id.action_splashFragment_to_loginFragment)
        } else {
            navigate(R.id.action_splashFragment_to_bottomNavFragment)
        }
    }
}