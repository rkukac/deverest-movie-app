package com.rkukac.movieapp.ui.navbar.favourites

import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.hilt.getViewModelFromFactory
import com.rkukac.movieapp.R
import com.rkukac.movieapp.databinding.FragmentFavouritesBinding
import com.rkukac.movieapp.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesFragment : RainbowCakeFragment<FavouritesViewState, FavouritesViewModel>() {

    private val binding by viewBinding(FragmentFavouritesBinding::bind)

    override fun getViewResource() = R.layout.fragment_favourites

    override fun provideViewModel() = getViewModelFromFactory()

    override fun render(viewState: FavouritesViewState) = Unit
}