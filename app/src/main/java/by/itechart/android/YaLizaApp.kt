package by.itechart.android

import android.app.Application
import by.itechart.android.di.dataModule
import by.itechart.android.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class YaLizaApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            printLogger()
            androidContext(this@YaLizaApp)
            modules(listOf(dataModule, viewModelModule))
        }
    }

}