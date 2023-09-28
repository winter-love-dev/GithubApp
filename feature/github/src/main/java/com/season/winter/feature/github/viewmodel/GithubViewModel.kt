package com.season.winter.feature.github.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.season.winter.githubapp.appcore.domain.github.entity.GithubSearchResponse
import com.season.winter.githubapp.appcore.domain.github.entity.GithubSearchUserSummaryEntity
import com.season.winter.githubapp.appcore.domain.github.entity.GithubUserEntity
import com.season.winter.githubapp.appcore.repository.github.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
    private val repository: GithubRepository,
): ViewModel() {


    private var _query = ""
    val query: String get() = _query

    val onQueryChangeTextListener = fun(query: String) {
        _query = query
        Log.e(TAG, "onQueryChangeTextListener: $query" )
        if (_query.isEmpty())
            clearSearchUserCache()
    }

    var _onClearedCache = MutableSharedFlow<Boolean>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST,
    )
    val onClearedCache: SharedFlow<Boolean>
        get() = _onClearedCache.asSharedFlow()

    suspend fun searchUser(query: String): Flow<GithubSearchResponse> {
        return repository.searchUser(query)
    }

    fun updateLikedState(user: GithubUserEntity) {
        viewModelScope.launch {
            repository.updateLikedState(user)
        }
    }

    fun clearSearchUserCache(query: String = _query) {
        viewModelScope.launch {
            repository.clearSearchUserCache(query)
            _onClearedCache.emit(true)
        }
    }

    fun getTotalCountStream(query: String = _query): Flow<GithubSearchUserSummaryEntity?> {
        return repository.getTotalCountStream(query)
    }

    fun getSearchUserStream(query: String = _query): Flow<PagingData<GithubUserEntity>> {
        return repository.getSearchUserResultStream(query).cachedIn(viewModelScope)
    }

    companion object {

        const val TAG = "GithubViewModel"
    }
}