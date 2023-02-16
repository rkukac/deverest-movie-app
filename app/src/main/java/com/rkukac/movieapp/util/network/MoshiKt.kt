package com.rkukac.movieapp.util

import com.rkukac.movieapp.data.network.adapter.BigDecimalJsonAdapter
import com.squareup.moshi.Moshi
import java.math.BigDecimal

val moshi: Moshi by lazy {
    Moshi.Builder().add(BigDecimal::class.java, BigDecimalJsonAdapter().nullSafe()).build()
}