package com.rkukac.movieapp.ui.navbar.adapter

import com.rkukac.movieapp.ui.navbar.model.UiMovie
import com.rkukac.movieapp.util.ui.loadImageUrl
import javax.inject.Inject

class MovieViewBinder @Inject constructor() {

    fun bind(
        holder: MovieViewHolder,
        movie: UiMovie,
        itemClickListener: MovieAdapterItemClickListener?
    ) = with(holder.itemBinding) {
        root.setOnClickListener { itemClickListener?.onItemClick(movie = movie) }
        movie.image?.let { imageListMovie.loadImageUrl(it) }
        textListTitle.text = movie.title
        textListSubtitle.text = movie.budgetString
    }
}