package com.season.winter.githubapp.appcore.repository.github

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.season.winter.githubapp.appcore.domain.github.domain.GithubRestApiService
import com.season.winter.githubapp.appcore.domain.github.entity.GithubUserEntity
import com.season.winter.githubapp.appcore.repository.github.paging.GithubPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val service: GithubRestApiService
) {

    fun getSearchUserResultStream(query: String): Flow<PagingData<GithubUserEntity>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = PageLimit),
            pagingSourceFactory = { GithubPagingSource(service, query) }
        ).flow
    }

    companion object {
        const val PageLimit = 30
    }
}