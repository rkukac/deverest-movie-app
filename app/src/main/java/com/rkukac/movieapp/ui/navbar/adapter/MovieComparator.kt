package com.rkukac.movieapp.ui.navbar.adapter

import androidx.recyclerview.widget.DiffUtil
import com.rkukac.movieapp.ui.navbar.model.UiMovie

object MovieComparator : DiffUtil.ItemCallback<UiMovie>() {

    override fun areItemsTheSame(oldItem: UiMovie, newItem: UiMovie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UiMovie, newItem: UiMovie): Boolean {
        return oldItem == newItem
    }
}