package com.rkukac.movieapp.util

import com.squareup.moshi.Moshi

val moshi: Moshi by lazy {
    Moshi.Builder().build()
}