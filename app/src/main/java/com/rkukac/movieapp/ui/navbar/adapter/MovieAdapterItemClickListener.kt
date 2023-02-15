package com.rkukac.movieapp.ui.navbar.adapter

import com.rkukac.movieapp.ui.navbar.model.UiMovie

fun interface MovieAdapterItemClickListener {

    fun onItemClick(movie: UiMovie)
}