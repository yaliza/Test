package by.itechart.android.di

import by.itechart.android.data.api.FacebookApi
import by.itechart.android.data.helper.UserHelper
import by.itechart.android.data.repository.Repository
import com.facebook.CallbackManager
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single { Repository(get(), get()) }
    single { UserHelper() }

    single { FacebookApi.Factory.create() }
    single { LoginManager.getInstance() }
    single { CallbackManager.Factory.create() }

    single {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestId()
            .requestProfile()
            .build()
    }
    single { GoogleSignIn.getClient(androidContext(), get()) }
}