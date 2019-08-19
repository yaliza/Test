package by.itechart.android.ui.screen.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.itechart.android.R
import by.itechart.android.data.entity.User
import by.itechart.android.ext.hide
import by.itechart.android.ext.navigate
import by.itechart.android.ext.show
import by.itechart.android.ui.base.ResourceObserver
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.view_progress_bar.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel by viewModel<LoginViewModel>()
    private val googleSignInClient by inject<GoogleSignInClient>()

    private val facebookLoginManager by inject<LoginManager>()
    private val facebookCallbackManager by inject<CallbackManager>()
    private val facebookRegistrationListener = object : FacebookCallback<LoginResult> {
        override fun onSuccess(result: LoginResult) { viewModel.getFacebookUser(result.accessToken) }
        override fun onCancel() {}
        override fun onError(error: FacebookException) = Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.user.observe(viewLifecycleOwner, object : ResourceObserver<User?>() {
            override fun onSuccess(data: User?) {
                progressBar.hide()
                data?.let { navigate(R.id.action_loginFragment_to_learningFragment) }
            }
            override fun onError(message: String) {
                progressBar.hide()
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            }
            override fun onLoading() = progressBar.show()
        })

        facebookButton.setOnClickListener {
            facebookLoginManager.logInWithReadPermissions(this, listOf("public_profile", "email"))
        }

        googleButton.setOnClickListener {
            startActivityForResult(googleSignInClient.signInIntent, LOGIN_GOOGLE_REQUEST_CODE)
        }
    }

    override fun onStart() {
        super.onStart()
        facebookLoginManager.registerCallback(facebookCallbackManager, facebookRegistrationListener)
    }

    override fun onStop() {
        super.onStop()
        facebookLoginManager.unregisterCallback(facebookCallbackManager)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_CANCELED) return
        when (requestCode) {
            LOGIN_GOOGLE_REQUEST_CODE -> viewModel.getGoogleUser(GoogleSignIn.getSignedInAccountFromIntent(data))
            else -> facebookCallbackManager.onActivityResult(requestCode, resultCode, data)
        }
    }

    companion object {
        private const val LOGIN_GOOGLE_REQUEST_CODE = 1
    }

}