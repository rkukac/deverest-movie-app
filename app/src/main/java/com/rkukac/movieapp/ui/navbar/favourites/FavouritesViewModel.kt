package com.rkukac.movieapp.ui.navbar.favourites

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val presenter: FavouritesPresenter
) : RainbowCakeViewModel<FavouritesViewState>(FavouritesViewState())