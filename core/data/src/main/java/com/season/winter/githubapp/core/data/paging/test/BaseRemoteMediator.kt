package com.season.winter.githubapp.core.data.paging.test

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.season.winter.githubapp.core.domain.entity.RemoteKey
import java.lang.Exception


@OptIn(ExperimentalPagingApi::class)
class BaseRemoteMediator<Key: Any, Value: Any>(
    val getPagingDataInitial: suspend (loadType: LoadType, state: PagingState<Key, Value>) -> MediatorResult,
    val getPagingDataAfter: suspend (loadType: LoadType, state: PagingState<Key, Value>) -> MediatorResult,
    val getKey: suspend (data: Value) -> RemoteKey?
): RemoteMediator<Key, Value>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.SKIP_INITIAL_REFRESH
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Key, Value>): MediatorResult {
        return try {
            when (getPageKey(loadType, state)) {
                null ->
                    getPagingDataInitial(loadType, state)
                -1 ->
                    MediatorResult.Success(endOfPaginationReached = true)
                else ->
                    getPagingDataAfter(loadType, state)
            }
        } catch (exception: Exception) {
            MediatorResult.Error(exception)
        }
    }

    suspend fun getPageKey(loadType: LoadType, state: PagingState<Key, Value>): Int? {
        return when(loadType) {
            LoadType.REFRESH -> {
                null
            }
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                remoteKeys?.prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                remoteKeys?.nextKey
            }
        }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Key, Value>): RemoteKey? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let {
                getKey(it)
            }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Key, Value>): RemoteKey? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let {
                getKey(it)
            }
    }

    companion object {
        const val PageLimit = 30
    }

}