package com.rkukac.movieapp.ui.navbar.popular

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val presenter: PopularPresenter
) : RainbowCakeViewModel<PopularViewState>(PopularViewState())