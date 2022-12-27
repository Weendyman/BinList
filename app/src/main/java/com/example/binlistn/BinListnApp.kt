package com.example.binlistn

import android.app.Application
import com.example.binlistn.di.dataModule
import com.example.binlistn.di.databaseModule
import com.example.binlistn.di.networkModule
import com.example.binlistn.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BinListnApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BinListnApp)
            modules(presentationModule, dataModule, networkModule, databaseModule)
        }
    }
}