package com.example.composeapplication

import android.app.Application
import com.example.composeapplication.compose.di.appModule
import com.example.composeapplication.flow.di.flowModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ComposeApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ComposeApplication)
            modules(appModule, flowModule)
        }
    }
}
