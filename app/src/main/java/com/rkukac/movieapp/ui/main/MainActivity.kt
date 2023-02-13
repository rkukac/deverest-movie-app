package com.rkukac.movieapp.ui.main

import co.zsmb.rainbowcake.base.RainbowCakeActivity
import co.zsmb.rainbowcake.hilt.getViewModelFromFactory
import com.rkukac.movieapp.databinding.ActivityMainBinding
import com.rkukac.movieapp.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : RainbowCakeActivity<MainViewState, MainActivityViewModel>() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun provideViewModel() = getViewModelFromFactory()

    override fun render(viewState: MainViewState) = Unit
}