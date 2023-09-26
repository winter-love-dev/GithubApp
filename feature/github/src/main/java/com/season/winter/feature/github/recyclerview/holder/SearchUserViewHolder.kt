package com.season.winter.feature.github.recyclerview.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.season.winter.feature.github.databinding.ItemSearchUserBinding
import com.season.winter.githubapp.appcore.domain.github.entity.GithubUserEntity

class SearchUserViewHolder(
    parent: ViewGroup,
    layoutInflater: LayoutInflater = LayoutInflater.from(parent.context),
    private val binding: ItemSearchUserBinding =
        ItemSearchUserBinding.inflate(layoutInflater, parent, false),
): ViewHolder(binding.root) {

    fun binding(userData: GithubUserEntity) {
        binding.name = userData.login
        binding.imageUrl = userData.avatarUrl
        binding.liked = if (layoutPosition == 3) true else userData.liked
    }
}