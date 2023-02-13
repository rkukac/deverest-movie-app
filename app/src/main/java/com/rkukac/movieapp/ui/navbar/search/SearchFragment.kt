package com.rkukac.movieapp.ui.navbar.search

import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.hilt.getViewModelFromFactory
import com.rkukac.movieapp.R
import com.rkukac.movieapp.databinding.FragmentSearchBinding
import com.rkukac.movieapp.util.ui.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : RainbowCakeFragment<SearchViewState, SearchViewModel>() {

    private val binding by viewBinding(FragmentSearchBinding::bind)

    override fun getViewResource() = R.layout.fragment_search

    override fun provideViewModel() = getViewModelFromFactory()

    override fun render(viewState: SearchViewState) = Unit
}