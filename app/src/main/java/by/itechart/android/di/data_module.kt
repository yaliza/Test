package by.itechart.android.di

import by.itechart.android.data.api.FacebookApi
import by.itechart.android.data.repository.Repository
import by.itechart.android.data.repository.UserHelper
import by.itechart.android.ui.mapper.*
import com.facebook.CallbackManager
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val dataModule = module {

    single { Repository(get(), get(), get()) }
    single { UserHelper() }

    //facebook login
    single { FacebookApi.Factory.create() }
    single { LoginManager.getInstance() }
    single { CallbackManager.Factory.create() }

    //google login
    single {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestId()
            .requestProfile()
            .build()
    }
    single { GoogleSignIn.getClient(androidContext(), get()) }

    single {
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    single { Firebase.firestore }

    //mappers
    single { LevelMapper(get()) }
    single { SociableMapper() }
    single { CertificateMapper() }
    single { ModalCardMapper() }
    single { LevelStatMapper(get()) }
}