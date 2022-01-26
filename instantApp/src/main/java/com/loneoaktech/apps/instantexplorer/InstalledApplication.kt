package com.loneoaktech.apps.instantexplorer

import android.app.Application
import timber.log.Timber

class InstalledApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Should be wrapped with if (BuildConfig.DEBUG)
        Timber.plant( Timber.DebugTree() )
    }
}