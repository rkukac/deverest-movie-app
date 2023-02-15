package com.rkukac.movieapp.ui.details

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val presenter: DetailsPresenter
) : RainbowCakeViewModel<DetailsViewState>(DetailsViewState()) {
}