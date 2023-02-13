package com.rkukac.movieapp.util.ui

import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ActivityViewBindingDelegate<T : ViewBinding>(
    val activity: FragmentActivity,
    val viewBindingFactory: (LayoutInflater) -> T
) : ReadOnlyProperty<FragmentActivity, T> {

    private var binding: T? = null

    override fun getValue(thisRef: FragmentActivity, property: KProperty<*>): T {
        return getInitializedBinding(thisRef)
    }

    private fun getInitializedBinding(activity: FragmentActivity): T {
        if (binding == null) {
            binding = viewBindingFactory(activity.layoutInflater)
        }
        return requireNotNull(binding)
    }

    private val defaultLifecycleObserver = object : DefaultLifecycleObserver {
        override fun onCreate(owner: LifecycleOwner) {
            activity.setContentView(getInitializedBinding(activity).root)
        }

        override fun onDestroy(owner: LifecycleOwner) {
            binding = null
        }
    }

    init {
        activity.lifecycle.addObserver(defaultLifecycleObserver)
    }
}

fun <T : ViewBinding> FragmentActivity.viewBinding(viewBindingFactory: (LayoutInflater) -> T) =
    ActivityViewBindingDelegate(this, viewBindingFactory)