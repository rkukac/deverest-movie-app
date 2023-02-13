package com.rkukac.movieapp.ui.navbar.search

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val presenter: SearchPresenter
) : RainbowCakeViewModel<SearchViewState>(SearchViewState())