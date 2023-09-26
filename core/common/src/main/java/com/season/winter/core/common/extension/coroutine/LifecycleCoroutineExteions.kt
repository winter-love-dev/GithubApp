package com.season.winter.core.common.extension.coroutine

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <T> LifecycleOwner.repeatOnLifecycle(
    flow: Flow<T>,
    repeatWhen: Lifecycle.State = Lifecycle.State.STARTED,
    callback: (item: T) -> Unit,
) {
    lifecycleScope.launch {
        repeatOnLifecycle(repeatWhen) {
            flow.collect {
                callback(it)
            }
        }
    }
}

fun LifecycleOwner.launchRepeatOnLifecycleStarted(
    callback: suspend LifecycleCoroutineScope.() -> Unit
): Job {
    return lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            callback(lifecycleScope)
        }
    }
}
