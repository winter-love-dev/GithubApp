package com.season.winter.githubapp.core.data.paging.test

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.season.winter.githubapp.core.data.paging.test.BaseRemoteMediator.Companion.PageLimit
import com.season.winter.githubapp.core.domain.entity.RemoteKey
import kotlinx.coroutines.flow.Flow

abstract class BaseRemoteMediatorFactory<Key: Any, Value: Any> {

    abstract suspend fun getRemoteKey(data: Value): RemoteKey?

    abstract fun createRemoteMediator(): BaseRemoteMediator<Key, Value>

    abstract fun getPagingSource(): PagingSource<Key, Value>

    @OptIn(ExperimentalPagingApi::class)
    fun getGenericPagingFlow(pageSize: Int = PageLimit): Flow<PagingData<Value>> {
        return Pager(
            config = getPagingConfig(pageSize),
            remoteMediator = createRemoteMediator()
        ) {
            getPagingSource()
        }.flow
    }

    private fun getPagingConfig(pageSize: Int = PageLimit): PagingConfig {
        return PagingConfig(
            pageSize = pageSize
        )
    }
}