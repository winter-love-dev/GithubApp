package com.season.winter.core.common.extension.coroutine

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

enum class FlowCollect {
    Collect,
    CollectLatest,
//    CollectIndexed
}

fun <T> LifecycleOwner.repeatOnLifecycleJob(
    flow: Flow<T>?,
    repeatWhen: Lifecycle.State = Lifecycle.State.STARTED,
    collect: FlowCollect = FlowCollect.Collect,
    callback: suspend LifecycleOwner.(item: T) -> Unit,
//    callback: suspend LifecycleOwner.(index: Int, item: T) -> Unit,
): Job {
    return lifecycleScope.launch {
        repeatOnLifecycle(repeatWhen) {
            when(collect) {
                FlowCollect.Collect -> flow?.collect {
                    callback(it)
                }
                FlowCollect.CollectLatest -> flow?.collectLatest {
                    callback(it)
                }
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
