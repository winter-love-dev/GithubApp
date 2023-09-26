package com.season.winter.feature.github.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.season.winter.feature.github.recyclerview.holder.SearchUserViewHolder
import com.season.winter.feature.github.recyclerview.holder.SummaryViewHolder
import com.season.winter.githubapp.appcore.domain.github.entity.GithubSearchResponse
import com.season.winter.githubapp.appcore.domain.github.entity.GithubUserEntity


class SearchGithubUserResultAdapter: ListAdapter<GithubUserEntity, RecyclerView.ViewHolder>(GithubUserDiffCallback()) {

    private var totalSearchResult = 0
    private var onClickLike: ((userId: Int) -> Unit)? = null

    override fun getItemCount(): Int = currentList.size + RemainingSummarySize

    override fun getItemViewType(position: Int): Int = when(position) {
        Summary -> Summary
        else -> User
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            Summary -> SummaryViewHolder(parent)
            else -> SearchUserViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SummaryViewHolder -> holder.binding(totalSearchResult)
            is SearchUserViewHolder -> holder.binding(currentList[position - RemainingSummarySize])
        }
    }

    fun addNewResult(data: GithubSearchResponse) = currentList.toMutableList().also {
        totalSearchResult = data.totalCount
        it.addAll(data.users)
        submitList(it)
    }

    fun refresh(data: GithubSearchResponse) {
        totalSearchResult = data.totalCount
        submitList(data.users)
    }

    companion object {
        private const val RemainingSummarySize = 1
        private const val Summary = 0
        private const val User = 1
    }

}