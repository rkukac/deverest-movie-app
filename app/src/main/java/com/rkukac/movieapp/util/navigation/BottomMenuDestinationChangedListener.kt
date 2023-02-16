package com.rkukac.movieapp.util.navigation

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.DialogFragmentNavigator
import com.rkukac.movieapp.R

class BottomMenuDestinationChangedListener(
    private val bottomBarVisibilityBlock: (bottomBarVisible: Boolean) -> Unit
) : NavController.OnDestinationChangedListener {

    private val bottomMenuScreenIds = listOf(
        R.id.popular_fragment,
        R.id.search_fragment,
        R.id.favourites_fragment
    )

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        bottomBarVisibilityBlock(isBottomBarVisible(controller, destination))
    }

    private fun isBottomBarVisible(
        controller: NavController,
        destination: NavDestination
    ): Boolean = destination.isBottomMenuFragment || destination.isBottomMenuBottomSheet(controller)

    private val NavDestination?.isBottomMenuFragment: Boolean
        get() = this != null && bottomMenuScreenIds.contains(id)

    private fun NavDestination.isBottomMenuBottomSheet(
        controller: NavController
    ): Boolean =
        isBottomSheet && controller.previousBackStackEntry?.destination.isBottomMenuFragment

    private val NavDestination.isBottomSheet: Boolean
        get() = this is DialogFragmentNavigator.Destination

}