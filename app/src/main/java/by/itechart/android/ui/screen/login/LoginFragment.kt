package by.itechart.android.ui.screen.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.itechart.android.R
import by.itechart.android.data.entity.User
import by.itechart.android.ext.navigateTo
import by.itechart.android.utils.ErrorHandlingObserver
import by.itechart.android.utils.Resource
import com.facebook.AccessToken
import com.facebook.AccessTokenTracker
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


private const val LOGIN_GOOGLE_REQUEST_CODE = 1
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel by viewModel<LoginViewModel>()
    private val googleSignInClient by inject<GoogleSignInClient>()

    private val accessTokenTracker = object : AccessTokenTracker() {
        override fun onCurrentAccessTokenChanged(oldAccessToken: AccessToken?, currentAccessToken: AccessToken?) {
            viewModel.setFacebookAccessToken(currentAccessToken)
        }
    }

    private val callbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setGoogleAccount(GoogleSignIn.getLastSignedInAccount(activity))
        viewModel.setFacebookAccessToken(AccessToken.getCurrentAccessToken())
        viewModel.profile.observe(this, object : ErrorHandlingObserver<User?>() {
            override fun onLoading(loading: User?) {}
            override fun onException(error: Resource.Error<User?>) {
                Toast.makeText(context, error.exception?.message, Toast.LENGTH_LONG).show()
            }
            override fun onSuccess(data: User?) {
                data?.let {
                    navigateTo(R.id.action_loginFragment_to_bottomNavFragment)
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        facebookButton.setPermissions(mutableListOf("public_profile", "email"))
//        facebookButton.fragment = this
        googleButton.setOnClickListener {
            startActivityForResult(googleSignInClient.signInIntent, LOGIN_GOOGLE_REQUEST_CODE)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            LOGIN_GOOGLE_REQUEST_CODE -> viewModel.setGoogleAccount(GoogleSignIn.getSignedInAccountFromIntent(data))
            else -> callbackManager.onActivityResult(requestCode, resultCode, data)
        }
    }

}