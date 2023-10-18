package com.season.winter.core.common.extension.coroutine

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <T> LifecycleOwner.repeatOnLifecycleJob(
    flow: Flow<T>?,
    repeatWhen: Lifecycle.State = Lifecycle.State.STARTED,
    callback: suspend LifecycleOwner.(item: T) -> Unit,
): Job {
    return lifecycleScope.launch {
        repeatOnLifecycle(repeatWhen) {
            flow?.collect {
                callback(it)
            }
        }
    }
}
