package com.season.winter.githubapp.core.domain

import androidx.paging.PagingData
import com.season.winter.githubapp.core.domain.entity.GithubSearchUserSummaryEntity
import com.season.winter.githubapp.core.domain.entity.GithubUserEntity
import kotlinx.coroutines.flow.Flow

interface GithubRepository {

    suspend fun updateLikedState(user: GithubUserEntity)
    suspend fun clearSearchUserCache(query: String)
    fun getTotalCountStream(query: String): Flow<GithubSearchUserSummaryEntity?>

    fun getSearchUserResultStream(query: String): Flow<PagingData<GithubUserEntity>>

}