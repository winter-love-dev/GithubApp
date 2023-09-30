package com.season.winter.feature.github.recyclerview

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.season.winter.feature.github.recyclerview.holder.SearchUserViewHolder
import com.season.winter.feature.github.viewmodel.GithubViewModel
import com.season.winter.githubapp.appcore.domain.github.entity.GithubUserEntity


class SearchGithubUserResultAdapter(
    private val viewModel: GithubViewModel
): PagingDataAdapter<GithubUserEntity, SearchUserViewHolder>(GithubUserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchUserViewHolder {
        return SearchUserViewHolder(parent)
    }

    override fun onBindViewHolder(holder: SearchUserViewHolder, position: Int) {
        getItem(position)?.let {
            holder.apply {
                binding(it)
                onClickLike = {
                    changeLiked(position)
                }
            }
        }
    }

    private fun changeLiked(position: Int) {
        (snapshot()[position] ?: return).also {
            viewModel.updateLikedState(it)
            it.liked = it.liked.not()
            notifyItemChanged(position)
        }
    }


//    fun addNewResult(data: GithubSearchResponse) = currentList.toMutableList().also {
//        totalSearchResult = data.totalCount
//        it.addAll(data.users)
//        submitList(it)
//    }
//
//    fun refresh(data: GithubSearchResponse){
//        totalSearchResult = data.totalCount
//        submitList(data.users)
//    }
//
//    fun clear() = currentList.toMutableList().also {
//        it.clear()
//        submitList(it)
//    }

    companion object {
        private const val RemainingSummarySize = 1
        private const val Summary = 0
        private const val User = 1
    }

}