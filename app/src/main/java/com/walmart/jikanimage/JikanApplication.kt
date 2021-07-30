package com.walmart.jikanimage

import android.app.Application
import com.walmart.jikanimage.modules.networkModule
import com.walmart.jikanimage.modules.repositoryModule
import com.walmart.jikanimage.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Application class
 *
 * we start koin modules here
 */
class JikanApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@JikanApplication)
            modules(networkModule, repositoryModule, viewModelModule)
        }
    }
}