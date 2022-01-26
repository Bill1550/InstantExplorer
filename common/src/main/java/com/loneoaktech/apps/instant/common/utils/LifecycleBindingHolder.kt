package com.loneoaktech.apps.instant.common.utils

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import timber.log.Timber

/**
 * Small class to create a lazy holder for a view binding
 * Allows view binding to be stored as a val.
 * Automatically cleans up when fragment is destroyed.
 */
class LifecycleBindingHolder<T : ViewBinding>(
    lifecycleOwner: LifecycleOwner,
    private val creator: ()->T
) {
    private var _binding: T? = null
    private var useCount: Int = 0

    init {
        lifecycleOwner.lifecycle.addObserver(
            object : LifecycleEventObserver {
                override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                    when (event) {
                        Lifecycle.Event.ON_DESTROY -> clear()
                        else -> {
                        } // don't care
                    }
                }
            }
        )
    }


    val binding: T
        get() {
            return _binding ?: let {
                useCount++
                if ( useCount > 1)
                    Timber.e("Binding holder reused! count=$useCount") // not a real error, just very interesting!
                creator().also { _binding = it }
            }
        }

    val root: View
        get() = binding.root

    fun clear() {
        Timber.i("clearing binding holder")
        _binding = null
    }
}


/**
 * creates a LifecycleBindingHolder.
 * Usage in a Fragment:
 *
 *    private val vbHolder = lazyViewBinding { FragmentLayoutBinding.inflate(layoutInflater)
 */
fun <T: ViewBinding> LifecycleOwner.lazyViewBinding(creator: ()->T): LifecycleBindingHolder<T>
    = LifecycleBindingHolder(this, creator)

fun <T: ViewBinding, U: Any?> LifecycleBindingHolder<T>.withViews(block: T.()->U): U {
    return this.binding.block()
}