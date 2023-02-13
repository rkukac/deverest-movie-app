package com.rkukac.movieapp.ui.main

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() :
    RainbowCakeViewModel<MainViewState>(MainViewState())