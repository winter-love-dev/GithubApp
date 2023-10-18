package com.season.winter.githubapp.core.data.paging.test

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.season.winter.githubapp.core.data.database.GithubLocalDatabase
import com.season.winter.githubapp.core.data.paging.STARTING_PAGING_INDEX
import com.season.winter.githubapp.core.domain.GithubApi
import com.season.winter.githubapp.core.domain.entity.GithubSearchUserSummaryEntity
import com.season.winter.githubapp.core.domain.entity.GithubUserEntity
import com.season.winter.githubapp.core.domain.entity.RemoteKey
import kotlin.math.ceil

@OptIn(ExperimentalPagingApi::class)
class GithubUserRemoteMediatorWithTemplate(
    private val query: String,
    private val database: GithubLocalDatabase,
    private val networkService: GithubApi
) {

    private val userDao = database.githubDao()
    private val remoteKeyDao = database.remoteKeyDao()

    val remoteMediatorFactory = object : BaseRemoteMediatorFactory<Int, GithubUserEntity>() {
        override fun createRemoteMediator(): BaseRemoteMediator<Int, GithubUserEntity> {
            return BaseRemoteMediator(
                getPagingDataInitial = ::requestGetEventWithTotalCount,
                getPagingDataAfter = ::requestGetEvent,
                getKey = ::getRemoteKey
            )
        }
        override suspend fun getRemoteKey(data: GithubUserEntity): RemoteKey? {
            return remoteKeyDao.remoteKeyByQuery("data.id")
        }
        override fun getPagingSource(): PagingSource<Int, GithubUserEntity> {
            return userDao.userPagingSource(query)
        }
    }


    suspend fun requestGetEventWithTotalCount(
        loadType: LoadType,
        state: PagingState<Int, GithubUserEntity>
    ): RemoteMediator.MediatorResult {
        return RemoteMediator.MediatorResult.Success(
            endOfPaginationReached = true
        )
    }

    suspend fun requestGetEvent(
        loadType: LoadType,
        state: PagingState<Int, GithubUserEntity>
    ): RemoteMediator.MediatorResult {

        val loadKey = when (loadType) {

            // REFRESH의 경우 null을 전달하여 첫 번째 페이지를 로드합니다
            LoadType.REFRESH ->
                null

            LoadType.PREPEND ->
                null
//                    return MediatorResult.Success(endOfPaginationReached = false)

            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                if (lastItem == null) {
                    return RemoteMediator.MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                }
                lastItem.id
            }
        }

        val response = networkService.searchUser(
            query = query,
            page = loadKey?.toInt() ?: STARTING_PAGING_INDEX
        )

        val totalPage = ceil(
            response.totalCount.toDouble() / BaseRemoteMediator.PageLimit
        ).toInt()


        database.withTransaction {
            if (loadType == LoadType.REFRESH) {
                remoteKeyDao.deleteByQuery(query)
                userDao.deleteUserUseQuery(query)
            }

            remoteKeyDao.insertOrReplace(
                RemoteKey(
                    query,
                    loadKey?.toInt(),
                    loadKey?.toInt(),
                )
            )

            userDao.insertSearchSummary(
                GithubSearchUserSummaryEntity(
                    query,
                    response.totalCount,
                    totalPage
                )
            )

            userDao.insertUserAll(response.users)
        }

//            MediatorResult.Success(
//                endOfPaginationReached = false
//            )

        return RemoteMediator.MediatorResult.Success(
            endOfPaginationReached = false
        )
    }

}
