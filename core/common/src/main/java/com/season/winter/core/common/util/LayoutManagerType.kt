package com.season.winter.core.common.util

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

enum class LayoutManagerType {
    Linear,
    Grid,
    Staggered
    ;

    fun isSameState(lastState: LayoutManagerType): Boolean = this == lastState

    fun getLayoutManager(
        context: Context,
        spanCount: Int = 2,
        orientation: Int = StaggeredGridLayoutManager.VERTICAL
    ): RecyclerView.LayoutManager =
        when (this) {
            Linear    -> LinearLayoutManager(context)
            Grid      -> GridLayoutManager(context, spanCount)
            Staggered -> StaggeredGridLayoutManager(spanCount, orientation)
        }
}

abstract class LayoutChangeManager(
    private var lastState: LayoutManagerType = LayoutManagerType.Linear
) {

    init {
        initLayout(lastState)
    }

    fun changeLayout(type: LayoutManagerType) {
        if (type.isSameState(lastState)) return
        else lastState = type
        onNewLayout(lastState)
    }

    abstract fun initLayout(newLayout: LayoutManagerType)
    abstract fun onNewLayout(newLayout: LayoutManagerType)
}

//
//
//fun changeLayoutManager(type: LayoutManagerType) {
//    if (type.isSameState(lastState)) return
//    else lastState = type
//    onNewLayoutManager?.invoke(lastState)
//}