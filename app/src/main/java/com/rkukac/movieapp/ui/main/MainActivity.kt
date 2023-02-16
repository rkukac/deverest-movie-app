package com.rkukac.movieapp.ui.main

import android.os.Bundle
import android.view.Gravity.BOTTOM
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.transition.Slide
import androidx.transition.TransitionManager
import co.zsmb.rainbowcake.base.RainbowCakeActivity
import co.zsmb.rainbowcake.hilt.getViewModelFromFactory
import com.rkukac.movieapp.databinding.ActivityMainBinding
import com.rkukac.movieapp.util.navigation.BottomMenuDestinationChangedListener
import com.rkukac.movieapp.util.ui.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : RainbowCakeActivity<MainViewState, MainActivityViewModel>() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private val bottomMenuDestinationChangedListener = BottomMenuDestinationChangedListener {
        animateBottomBarVisibility(it)
    }

    override fun provideViewModel() = getViewModelFromFactory()

    override fun render(viewState: MainViewState) = Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupNavController()
    }

    private fun setupNavController() {
        val navController = getNavHostFragment().navController.apply {
            addOnDestinationChangedListener(bottomMenuDestinationChangedListener)
        }
        binding.bottomNavigationView.apply {
            setupWithNavController(navController)
            itemIconTintList = null
        }
    }

    private fun getNavHostFragment() =
        binding.fragmentContainerView.getFragment() as NavHostFragment

    private fun animateBottomBarVisibility(visible: Boolean) = with(binding) {
        if (bottomNavigationView.isVisible == visible) return@with
        TransitionManager.beginDelayedTransition(
            root,
            Slide(BOTTOM).excludeChildren(fragmentContainerView, true)
        )
        bottomNavigationView.isVisible = visible
    }
}