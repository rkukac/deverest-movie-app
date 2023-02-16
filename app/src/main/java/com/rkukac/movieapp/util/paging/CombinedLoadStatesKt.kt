package com.rkukac.movieapp.util.paging

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState

val CombinedLoadStates.refreshOrAppendLoading
    get() = if (refresh is LoadState.Loading) {
        refresh
    } else {
        append
    }

val CombinedLoadStates.isRefreshError
    get() = refresh is LoadState.Error