package com.rkukac.movieapp.ui.navbar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rkukac.movieapp.databinding.LayoutRowMovieBinding

class MovieViewHolder(
    val itemBinding: LayoutRowMovieBinding,
) : RecyclerView.ViewHolder(itemBinding.root) {

    companion object {
        fun inflateLayout(parent: ViewGroup) = LayoutRowMovieBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    }
}