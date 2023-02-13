package com.rkukac.movieapp

import android.app.Application
import androidx.viewbinding.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MovieAppApplication : Application() {

    private val isDebugEnabled = BuildConfig.DEBUG

    override fun onCreate() {
        super.onCreate()

        initTimber()
    }

    private fun initTimber() {
        if (isDebugEnabled.not()) {
            return
        }
        Timber.plant(Timber.DebugTree())
    }
}