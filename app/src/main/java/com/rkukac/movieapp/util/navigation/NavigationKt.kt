package com.rkukac.movieapp.util.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import timber.log.Timber

fun Fragment.navigateSafe(
    navController: NavController = findNavController(),
    navigateBlock: NavController.() -> Unit
) {
    when (val destination = navController.currentDestination) {
        is FragmentNavigator.Destination -> {
            checkDestination(navController, destination.className, navigateBlock)
        }
        else -> navigateBlock(navController)
    }
}

private fun Fragment.checkDestination(
    navController: NavController,
    destinationClassName: String,
    navigateBlock: NavController.() -> Unit
) {
    if (javaClass.name == destinationClassName) {
        navigateBlock(navController)
    } else {
        Timber.d(
            "Current class '${javaClass.name}' is other than expected " +
                    "'$destinationClassName'!"
        )
    }
}