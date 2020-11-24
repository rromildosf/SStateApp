package com.rsf.challengeapp

import android.app.Application
import com.rsf.challengeapp.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ChallengeApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@ChallengeApplication)

            modules(AppModule().subModules)
        }
    }

}