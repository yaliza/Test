package by.itechart.android.di

import by.itechart.android.ui.screen.login.LoginViewModel
import by.itechart.android.ui.screen.main.learning.LearningViewModel
import by.itechart.android.ui.screen.main.profile.ProfileViewModel
import by.itechart.android.ui.screen.modal.ModalViewModel
import by.itechart.android.ui.screen.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {

    viewModel { ModalViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { LearningViewModel(get()) }
}