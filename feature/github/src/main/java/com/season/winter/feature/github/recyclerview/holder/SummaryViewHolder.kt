package com.season.winter.feature.github.recyclerview.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.season.winter.feature.github.databinding.ItemSummaryBinding

class SummaryViewHolder(
    parent: ViewGroup,
    layoutInflater: LayoutInflater = LayoutInflater.from(parent.context),
    private val binding: ItemSummaryBinding =
        ItemSummaryBinding.inflate(layoutInflater, parent, false),
): ViewHolder(binding.root) {

    fun binding(totalCount: Int) {
        binding.totalCount = totalCount.toString()
    }
}