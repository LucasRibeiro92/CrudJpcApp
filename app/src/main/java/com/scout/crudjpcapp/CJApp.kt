package com.scout.crudjpcapp

import android.app.Application
import com.scout.crudjpcapp.model.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CJApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CJApp)
            modules(appModule)
        }
    }
}