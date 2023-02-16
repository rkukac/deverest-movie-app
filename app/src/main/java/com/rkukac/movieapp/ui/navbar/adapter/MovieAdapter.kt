package com.rkukac.movieapp.ui.navbar.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.rkukac.movieapp.ui.navbar.model.UiMovie
import javax.inject.Inject

class MovieAdapter @Inject constructor(
    private val viewHolderFactory: MovieViewHolderFactory,
    private val viewBinder: MovieViewBinder
) : PagingDataAdapter<UiMovie, MovieViewHolder>(MovieComparator) {

    private var movieAdapterItemClickListener: MovieAdapterItemClickListener? = null

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let {
            viewBinder.bind(
                holder = holder,
                movie = it,
                itemClickListener = movieAdapterItemClickListener
            )
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        viewHolderFactory.create(parent = parent)

    fun setItemClickListener(listener: MovieAdapterItemClickListener) {
        movieAdapterItemClickListener = listener
    }
}