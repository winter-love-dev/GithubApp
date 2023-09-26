package com.season.winter.feature.github.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.season.winter.githubapp.appcore.domain.github.entity.GithubUserEntity

class GithubUserDiffCallback: DiffUtil.ItemCallback<GithubUserEntity>() {

    override fun areItemsTheSame(oldItem: GithubUserEntity, newItem: GithubUserEntity): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: GithubUserEntity, newItem: GithubUserEntity): Boolean =
        oldItem.id == newItem.id
}