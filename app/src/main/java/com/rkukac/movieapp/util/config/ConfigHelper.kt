package com.rkukac.movieapp.util.config

import android.content.Context
import androidx.annotation.IntegerRes
import androidx.paging.PagingConfig
import com.rkukac.movieapp.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ConfigHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val defaultPageSize: Int
        get() = getIntegerResource(R.integer.config_page_size)


    fun provideDefaultPageConfig(): PagingConfig = PagingConfig(
        pageSize = defaultPageSize,
        enablePlaceholders = false,
        initialLoadSize = defaultPageSize
    )

    private fun getIntegerResource(@IntegerRes id: Int): Int = context.resources.getInteger(id)
}