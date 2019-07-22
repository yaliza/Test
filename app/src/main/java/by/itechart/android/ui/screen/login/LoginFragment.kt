package by.itechart.android.ui.screen.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.itechart.android.R
import by.itechart.android.data.entity.User
import by.itechart.android.ext.navigate
import by.itechart.android.utils.ErrorHandlingObserver
import by.itechart.android.utils.Resource
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


private const val LOGIN_GOOGLE_REQUEST_CODE = 1

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel by viewModel<LoginViewModel>()
    private val googleSignInClient by inject<GoogleSignInClient>()

    private val facebookLoginManager by inject<LoginManager>()
    private val facebookCallbackManager by inject<CallbackManager>()
    private val facebookRegistrationListener = object : FacebookCallback<LoginResult> {
        override fun onSuccess(result: LoginResult?) = viewModel.setFacebookAccessToken(result?.accessToken)
        override fun onCancel() {}
        override fun onError(error: FacebookException?) =
            Toast.makeText(context, error?.message, Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setGoogleAccount(GoogleSignIn.getLastSignedInAccount(activity))
        viewModel.setFacebookAccessToken(AccessToken.getCurrentAccessToken())

        viewModel.profile.observe(this, object : ErrorHandlingObserver<User?>() {
            override fun onLoading(loading: User?) {}
            override fun onException(error: Resource.Error<User?>) =
                Toast.makeText(context, error.exception?.message, Toast.LENGTH_LONG).show()

            override fun onSuccess(data: User?) {
                if (data != null) navigate(R.id.action_loginFragment_to_bottomNavFragment)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        when (requestCode) {
            LOGIN_GOOGLE_REQUEST_CODE -> viewModel.setGoogleAccount(GoogleSignIn.getSignedInAccountFromIntent(data))
            else -> facebookCallbackManager.onActivityResult(requestCode, resultCode, data)
        }
    }

}