package com.season.winter.githubapp.core.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.season.winter.githubapp.core.data.database.GithubLocalDatabase
import com.season.winter.githubapp.core.data.paging.test.BaseRemoteMediator.Companion.PageLimit
import com.season.winter.githubapp.core.domain.GithubRestApiService
import com.season.winter.githubapp.core.domain.entity.GithubSearchUserSummaryEntity
import com.season.winter.githubapp.core.domain.entity.GithubUserEntity
import com.season.winter.githubapp.core.domain.entity.RemoteKey
import retrofit2.HttpException
import java.io.IOException
import kotlin.math.ceil

@OptIn(ExperimentalPagingApi::class)
class GithubUserRemoteMediator(
    private val query: String,
    private val database: GithubLocalDatabase,
    private val networkService: GithubRestApiService
) : RemoteMediator<Int, GithubUserEntity>() {

    private val userDao = database.githubDao()
    private val remoteKeyDao = database.remoteKeyDao()

    private suspend fun clearCache() {
        database.withTransaction {
            remoteKeyDao.deleteByQuery(query)
            userDao.deleteUserUseQuery(query)
            userDao.deleteSummary(query)
        }
    }

    override suspend fun initialize(): InitializeAction {

        // 새 키워드로 검색하면 이전 캐시 지우기
        clearCache()
        return InitializeAction.SKIP_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GithubUserEntity>
    ): MediatorResult {
        return try {

            val remoteKey = remoteKeyDao.remoteKeyByQuery(query)

            val loadNextKey = when (loadType) {
                LoadType.REFRESH ->
                    null // REFRESH의 경우 null을 전달하여 첫 번째 페이지를 로드합니다

                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)

                LoadType.APPEND -> {
//                    if (remoteKey == null)
//                        return MediatorResult.Success(endOfPaginationReached = true)

                    remoteKey?.nextKey
                }
            }

            val currentKey = loadNextKey ?: STARTING_PAGING_INDEX
            val nextKey = if(loadNextKey == null) 1 else (currentKey + 1)

            // 끝 페이지 확인
            val lastSummary = userDao.checkSearchSummary(query)
            val isEndOfPaginationReached =
                if(loadNextKey == null) false
                else lastSummary.totalPage <= currentKey

            if (isEndOfPaginationReached)
                return MediatorResult.Success(endOfPaginationReached = true)

            // api
            val response = networkService.searchUser(query, page = nextKey)
            if (response.users.isEmpty())
                return MediatorResult.Success(endOfPaginationReached = true)

            val responseUsers: List<GithubUserEntity> = response.users.map { it.copy(query = query) }

            database.withTransaction {
                val userIds: List<Long> = responseUsers.map { it.id ?: 0L }
                val likeEntity = userDao.checkLiked(userIds)

                // id 기반 liked 값 갱신
                likeEntity?.forEach { likeEntity ->
                    responseUsers.filter { it.id == likeEntity.id }.forEach { userEntity ->
                        userEntity.liked = likeEntity.liked
                    }
                }
            }

            val calculateTotalPage = ceil(response.totalCount.toDouble() / PageLimit).toInt()

            database.withTransaction {
                if (loadType == LoadType.REFRESH)
                    clearCache()

                val newRemoteKey = RemoteKey(query,nextKey - 1, nextKey)
                remoteKeyDao.insertOrReplace(newRemoteKey)

                val summary = GithubSearchUserSummaryEntity(query, response.totalCount, calculateTotalPage)
                userDao.insertSearchSummary(summary)

                userDao.insertUserAll(responseUsers)
            }

            MediatorResult.Success(endOfPaginationReached = false)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}