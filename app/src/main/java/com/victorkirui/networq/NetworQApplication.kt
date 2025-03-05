package com.victorkirui.networq

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NetworQApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@NetworQApplication)
            modules(appModule)
        }
    }
}