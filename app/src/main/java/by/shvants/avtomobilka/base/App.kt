package by.shvants.avtomobilka.base

import android.app.Application
import androidx.navigation.NavController
import by.shvants.avtomobilka.di.appModule
import by.shvants.avtomobilka.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    appModule,
                )
            )
        }
    }
}