package com.yaritzama.marvelapp

import android.app.Application
import com.aptivist.challengeapis.core.coin.appModule
import com.aptivist.challengeapis.core.coin.databaseModule
import com.aptivist.challengeapis.core.coin.repositoryModule
import com.yaritzama.marvelapp.core.coin.dispatcherModule
import com.yaritzama.marvelapp.core.coin.networkModuleRetrofit
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class MyApplication: Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MyApplication)
            modules(networkModuleRetrofit, repositoryModule, appModule, databaseModule, dispatcherModule)
        }

    }

}