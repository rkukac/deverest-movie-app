package com.rkukac.movieapp.ui.navbar.popular

import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.hilt.getViewModelFromFactory
import com.rkukac.movieapp.R
import com.rkukac.movieapp.databinding.FragmentPopularBinding
import com.rkukac.movieapp.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularFragment : RainbowCakeFragment<PopularViewState, PopularViewModel>() {

    private val binding by viewBinding(FragmentPopularBinding::bind)

    override fun getViewResource() = R.layout.fragment_popular

    override fun provideViewModel() = getViewModelFromFactory()

    override fun render(viewState: PopularViewState) = Unit
}