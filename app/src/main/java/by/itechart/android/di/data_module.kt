package by.itechart.android.di

import by.itechart.android.data.api.FacebookApi
import by.itechart.android.data.repository.Repository
import org.koin.dsl.module

val dataModule = module {

    single { Repository(get()) }
    single { FacebookApi.Factory.create() }
}