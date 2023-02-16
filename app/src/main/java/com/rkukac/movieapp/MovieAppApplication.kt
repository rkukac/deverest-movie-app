package com.rkukac.movieapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewbinding.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MovieAppApplication : Application() {

    private val isDebugEnabled = BuildConfig.DEBUG

    override fun onCreate() {
        super.onCreate()

        disableForcedNightMode()
        initTimber()
    }

    private fun disableForcedNightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun initTimber() {
        if (isDebugEnabled.not()) {
            return
        }
        Timber.plant(Timber.DebugTree())
    }
}