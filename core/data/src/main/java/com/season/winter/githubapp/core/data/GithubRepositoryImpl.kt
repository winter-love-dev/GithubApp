package com.season.winter.githubapp.core.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.room.withTransaction
import com.season.winter.githubapp.core.data.database.GithubLocalDatabase
import com.season.winter.githubapp.core.data.paging.GithubUserRemoteMediator
import com.season.winter.githubapp.core.data.paging.test.BaseRemoteMediator.Companion.PageLimit
import com.season.winter.githubapp.core.domain.GithubApi
import com.season.winter.githubapp.core.domain.GithubRepository
import com.season.winter.githubapp.core.domain.entity.GithubSearchUserSummaryEntity
import com.season.winter.githubapp.core.domain.entity.GithubUserEntity
import com.season.winter.githubapp.core.domain.entity.GithubUserLikeEntity
import kotlinx.coroutines.flow.Flow

class GithubRepositoryImpl(
    private val remoteDao: GithubApi,
    private val database: GithubLocalDatabase,
): GithubRepository {

    private val userDao = database.githubDao()
    private val remoteKeyDao = database.remoteKeyDao()

    override suspend fun updateLikedState(user: GithubUserEntity) {
        val id = user.id ?: return
        userDao.updateLikedState(
            GithubUserLikeEntity(
                id,
                user.liked.not()
            )
        )
    }

    override suspend fun clearSearchUserCache(query: String) {
        database.withTransaction {
            remoteKeyDao.deleteByQuery(query)
            userDao.deleteUserUseQuery(query)
            userDao.deleteSummary(query)
        }
    }

    override fun getTotalCountStream(query: String): Flow<GithubSearchUserSummaryEntity?> {
        return userDao.checkSearchSummaryStream(query)
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getSearchUserResultStream(query: String): Flow<PagingData<GithubUserEntity>> {
        return Pager(
            config = PagingConfig(pageSize = PageLimit),
            remoteMediator = GithubUserRemoteMediator(query, database, remoteDao,),
        ) {
            userDao.userPagingSource(query)
        }.flow
    }

}