package by.itechart.android.di

import by.itechart.android.ui.screen.login.LoginViewModel
import by.itechart.android.ui.screen.modal.ModalViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {

    viewModel { ModalViewModel(get()) }
    viewModel { LoginViewModel(get()) }
}