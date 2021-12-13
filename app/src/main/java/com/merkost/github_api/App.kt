package com.merkost.github_api

import android.app.Application
import com.merkost.github_api.di.appModule
import com.merkost.github_api.di.mainActivity
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appModule, mainActivity)
        }
    }

}