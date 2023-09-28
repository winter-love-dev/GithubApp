package com.season.winter.githubapp.appcore.repository.github

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.room.withTransaction
import com.season.winter.githubapp.appcore.domain.github.GithubRestApiService
import com.season.winter.githubapp.appcore.domain.github.entity.GithubSearchUserSummaryEntity
import com.season.winter.githubapp.appcore.domain.github.entity.GithubUserEntity
import com.season.winter.githubapp.appcore.domain.github.entity.GithubUserLikeEntity
import com.season.winter.githubapp.appcore.repository.github.database.GithubLocalDatabase
import com.season.winter.githubapp.appcore.repository.github.paging.GithubUserRemoteMediator
import com.season.winter.githubapp.appcore.repository.github.paging.test.BaseRemoteMediator.Companion.PageLimit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val remoteDao: GithubRestApiService,
    private val database: GithubLocalDatabase,
) {

    private val userDao = database.githubDao()
    private val remoteKeyDao = database.remoteKeyDao()

    suspend fun updateLikedState(user: GithubUserEntity) {
        val id = user.id ?: return
        userDao.updateLikedState(
            GithubUserLikeEntity(
                id,
                user.liked.not()
            )
        )
    }

    suspend fun clearSearchUserCache(query: String) {
        database.withTransaction {
            remoteKeyDao.deleteByQuery(query)
            userDao.deleteUserUseQuery(query)
            userDao.deleteSummary(query)
        }
    }

    fun getTotalCountStream(query: String): Flow<GithubSearchUserSummaryEntity?> {
        return userDao.checkSearchSummaryStream(query)
    }

    suspend fun searchUser(query: String) = flow {
        val result = remoteDao.searchUser(query, PageLimit, 1)
        emit(result)
    }.flowOn(Dispatchers.IO)

    @OptIn(ExperimentalPagingApi::class)
    fun getSearchUserResultStream(query: String): Flow<PagingData<GithubUserEntity>> {
        return Pager(
            config = PagingConfig(pageSize = PageLimit),
            remoteMediator = GithubUserRemoteMediator(query, database, remoteDao,),
            // pagingSourceFactory = { localDao.userPagingSource(query) }
        ) {
            userDao.userPagingSource(query)
        }.flow
    }
}