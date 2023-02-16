package com.rkukac.movieapp.ui.navbar.adapter

import android.view.ViewGroup
import javax.inject.Inject

class MovieViewHolderFactory @Inject constructor() {

    fun create(
        parent: ViewGroup
    ) = MovieViewHolder(itemBinding = MovieViewHolder.inflateLayout(parent))
}