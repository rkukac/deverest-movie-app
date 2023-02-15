package com.rkukac.movieapp.ui.navbar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rkukac.movieapp.databinding.LayoutRowMovieLoaderBinding
import javax.inject.Inject

class MovieLoaderAdapter @Inject constructor() :
    LoadStateAdapter<MovieLoaderAdapter.MovieLoaderViewHolder>() {

    override fun onBindViewHolder(holder: MovieLoaderViewHolder, loadState: LoadState) {
        holder.apply {
            itemView.isVisible = loadState is LoadState.Loading
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): MovieLoaderViewHolder {
        val itemBinding = LayoutRowMovieLoaderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieLoaderViewHolder(itemBinding)
    }

    class MovieLoaderViewHolder(
        itemBinding: LayoutRowMovieLoaderBinding
    ) : RecyclerView.ViewHolder(itemBinding.root)
}