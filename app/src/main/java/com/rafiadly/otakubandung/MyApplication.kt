package com.rafiadly.otakubandung

import android.app.Application
import com.rafiadly.otakubandung.core.di.databaseModule
import com.rafiadly.otakubandung.core.di.networkModule
import com.rafiadly.otakubandung.core.di.repositoryModule
import com.rafiadly.otakubandung.di.useCaseModule
import com.rafiadly.otakubandung.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                )
            )
        }
    }
}