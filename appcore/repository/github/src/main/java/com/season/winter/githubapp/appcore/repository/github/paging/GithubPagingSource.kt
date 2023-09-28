package com.season.winter.githubapp.appcore.repository.github.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.season.winter.githubapp.appcore.domain.github.GithubLocalDao
import com.season.winter.githubapp.appcore.domain.github.GithubRestApiService
import com.season.winter.githubapp.appcore.domain.github.entity.GithubUserEntity
import com.season.winter.githubapp.appcore.repository.github.GithubRepository
import com.season.winter.githubapp.appcore.repository.github.paging.test.BaseRemoteMediator.Companion.PageLimit
import kotlin.math.ceil

const val STARTING_PAGING_INDEX = 1

class GithubPagingSource(
    private val service: GithubRestApiService,
    private val query: String,
    private val localDao: GithubLocalDao,
) : PagingSource<Int, GithubUserEntity>()  {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubUserEntity> {
        val page = params.key ?: STARTING_PAGING_INDEX
        return try {
            val response = service.searchUser(query, page, params.loadSize)
            val usersFromRetrofitRestApi: List<GithubUserEntity> = response.users

            val userIds: List<Long> = usersFromRetrofitRestApi.map { it.id ?: 0L }
            val searchResultFromRoomDatabase = localDao.checkLiked(userIds)
//            val combinedUsers: List<GithubUserEntity> = (usersFromRetrofitRestApi + searchResultFromRoomDatabase).distinct()

            val totalPage = ceil(
                response.totalCount.toDouble() / PageLimit
            ).toInt()


            // 결과 없거나, 끝 페이지에 왔을 경우 Null
            val nextKey = if (usersFromRetrofitRestApi.isEmpty() || page == totalPage)
                null
            else
                page + 1

            LoadResult.Page(
//                data = combinedUsers,
                data = emptyList(),
                prevKey = if (page == STARTING_PAGING_INDEX) null else page - 1,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            Log.e("TAG", "load: $exception", )
            LoadResult.Error(exception)
        }
    }

    fun clear() {
        invalidate()
    }

    override fun getRefreshKey(state: PagingState<Int, GithubUserEntity>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}