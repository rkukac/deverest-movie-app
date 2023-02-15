package com.rkukac.movieapp.util.paging.common

interface PagingSourceListener {

    fun onListEmpty()

    fun onInitialDataReceived()
}