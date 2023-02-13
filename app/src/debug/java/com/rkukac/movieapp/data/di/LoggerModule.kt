package com.rkukac.movieapp.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoggerModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptorLevel(): HttpLoggingInterceptor.Level =
        HttpLoggingInterceptor.Level.BODY
}