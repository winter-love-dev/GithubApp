package com.season.winter.core.common.bindingAdapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.season.winter.core.common.util.LayoutChangeManager
import com.season.winter.core.common.util.LayoutManagerType

@BindingAdapter("changeLayoutManagerFromType")
fun changeLayoutManagerFromType(
    view: RecyclerView,
    layoutManagerType: LayoutManagerType = LayoutManagerType.Linear
) {
    object : LayoutChangeManager(layoutManagerType) {
        override fun initLayout(newLayout: LayoutManagerType) {
            view.layoutManager = newLayout.getLayoutManager(view.context)
        }
        override fun onNewLayout(newLayout: LayoutManagerType) {
            view.layoutManager = newLayout.getLayoutManager(view.context)
        }
    }
}