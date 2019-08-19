package by.itechart.android.di

import by.itechart.android.ui.screen.login.LoginViewModel
import by.itechart.android.ui.screen.main.community.CommunityViewModel
import by.itechart.android.ui.screen.main.learning.LearningViewModel
import by.itechart.android.ui.screen.main.profile.ProfileViewModel
import by.itechart.android.ui.screen.modal.ModalViewModel
import by.itechart.android.ui.screen.module.ModuleViewModel
import by.itechart.android.ui.screen.quiz.QuizResultViewModel
import by.itechart.android.ui.screen.quiz.QuizViewModel
import by.itechart.android.ui.screen.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {

    viewModel { ModalViewModel(get(), get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { ProfileViewModel(get(), get(), get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { LearningViewModel(get(), get()) }
    viewModel { ModuleViewModel(get()) }
    viewModel { CommunityViewModel() }
    viewModel { QuizViewModel(get()) }
    viewModel { QuizResultViewModel(get(), get()) }
}