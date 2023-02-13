package com.rkukac.movieapp.ui.main

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import co.zsmb.rainbowcake.base.RainbowCakeActivity
import co.zsmb.rainbowcake.hilt.getViewModelFromFactory
import com.rkukac.movieapp.databinding.ActivityMainBinding
import com.rkukac.movieapp.util.ui.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : RainbowCakeActivity<MainViewState, MainActivityViewModel>() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun provideViewModel() = getViewModelFromFactory()

    override fun render(viewState: MainViewState) = Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupNavController()
    }

    private fun setupNavController() {
        binding.bottomNavigationView.setupWithNavController(getNavHostFragment().navController)
    }

    private fun getNavHostFragment() =
        binding.fragmentContainerView.getFragment() as NavHostFragment
}