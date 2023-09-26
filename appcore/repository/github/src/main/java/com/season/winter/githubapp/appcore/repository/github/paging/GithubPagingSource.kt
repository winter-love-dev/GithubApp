package com.season.winter.githubapp.appcore.repository.github.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.season.winter.githubapp.appcore.domain.github.domain.GithubRestApiService
import com.season.winter.githubapp.appcore.domain.github.entity.GithubUserEntity
import com.season.winter.githubapp.appcore.repository.github.GithubRepository
import kotlin.math.ceil

private const val STARTING_PAGING_INDEX = 1

internal class GithubPagingSource(
    private val service: GithubRestApiService,
    private val query: String
) : PagingSource<Int, GithubUserEntity>()  {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubUserEntity> {
        val page = params.key ?: STARTING_PAGING_INDEX
        return try {
            val response = service.searchUser(query, page, params.loadSize)
            val photos = response.users

            val totalPage = ceil(
                response.totalCount.toDouble() / GithubRepository.PageLimit
            ).toInt()

            LoadResult.Page(
                data = photos,
                prevKey = if (page == STARTING_PAGING_INDEX) null else page - 1,
                nextKey = if (page == totalPage) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GithubUserEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

}