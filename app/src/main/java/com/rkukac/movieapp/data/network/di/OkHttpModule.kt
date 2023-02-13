package com.rkukac.movieapp.data.network.di

import android.content.Context
import com.rkukac.movieapp.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OkHttpModule {

    @Provides
    @IntoSet
    fun provideHttpLoggingInterceptor(logLevel: HttpLoggingInterceptor.Level): Interceptor =
        HttpLoggingInterceptor().setLevel(logLevel)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        interceptorSet: Set<@JvmSuppressWildcards Interceptor>
    ): OkHttpClient {
        val connectionTimeoutMs = context.connectionTimeoutMs
        return OkHttpClient.Builder()
            .connectTimeout(connectionTimeoutMs, TimeUnit.MILLISECONDS)
            .readTimeout(connectionTimeoutMs, TimeUnit.MILLISECONDS)
            .writeTimeout(connectionTimeoutMs, TimeUnit.MILLISECONDS)
            .apply {
                interceptorSet.forEach { interceptor ->
                    addInterceptor(interceptor)
                }
            }.build()
    }
}

private val Context.connectionTimeoutMs: Long
    get() = resources.getInteger(R.integer.config_network_timeout_ms).toLong()