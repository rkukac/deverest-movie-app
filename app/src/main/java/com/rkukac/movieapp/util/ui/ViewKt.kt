package com.rkukac.movieapp.util.ui

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.ViewAnimator
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

fun ImageView.loadImageUrl(iconUrl: String) {
    Glide.with(context)
        .load(iconUrl)
        .into(this)
}

var ViewAnimator.displayedChildIfDifferent: Int
    get() = displayedChild
    set(value) {
        displayChildIfDifferent(value)
    }

private fun ViewAnimator.displayChildIfDifferent(child: Int): Boolean {
    return if (displayedChild != child) {
        displayedChild = child
        true
    } else {
        false
    }
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

private fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}