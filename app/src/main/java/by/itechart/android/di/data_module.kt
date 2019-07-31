package by.itechart.android.di

import by.itechart.android.data.api.FacebookApi
import by.itechart.android.data.mock.Levels
import by.itechart.android.data.repository.Repository
import by.itechart.android.data.repository.UserHelper
import by.itechart.android.ui.mapper.LevelMapper
import com.facebook.CallbackManager
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single { Repository(get(), get(), get()) }
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

    single { Levels(get()) }
    single {
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    single { LevelMapper(get()) }
}