package com.icdominguez.scribbledash

import android.app.Application
import com.icdominguez.scribbledash.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class ScribbleDashApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ScribbleDashApplication)
            modules(appModule)
        }
    }
}