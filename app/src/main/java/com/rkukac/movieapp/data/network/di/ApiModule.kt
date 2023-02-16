package com.rkukac.movieapp.data.network.di

import com.rkukac.movieapp.data.network.api.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MovieApi = retrofit.create()
}