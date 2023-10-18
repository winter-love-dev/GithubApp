package com.season.winter.feature.github.recyclerview.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.season.winter.feature.github.databinding.ItemSearchUserBinding
import com.season.winter.githubapp.core.domain.entity.GithubUserEntity

class SearchUserViewHolder(
    parent: ViewGroup,
    layoutInflater: LayoutInflater = LayoutInflater.from(parent.context),
    private val binding: ItemSearchUserBinding =
        ItemSearchUserBinding.inflate(layoutInflater, parent, false),
): ViewHolder(binding.root) {


    private var userData: GithubUserEntity? = null
    var onClickLike: (() -> Unit)? = null

    init {
        binding.viewHolder = this
    }

    fun onClickLiked() {
        onClickLike?.invoke()
    }


    fun binding(userData: GithubUserEntity) {
        this.userData = userData
        binding.name = """
            login: ${userData.login}
            nodeId: ${userData.nodeId}
            ${userData.url}
        """.trimIndent()
        binding.imageUrl = userData.avatarUrl
        binding.liked = userData.liked
//        binding.executePendingBindings() // 즉각 UI 갱신
    }
}