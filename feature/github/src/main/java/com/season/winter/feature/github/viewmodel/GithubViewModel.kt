package com.season.winter.feature.github.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.season.winter.githubapp.core.data.GithubRepository
import com.season.winter.githubapp.core.domain.entity.GithubSearchUserSummaryEntity
import com.season.winter.githubapp.core.domain.entity.GithubUserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
    private val repository: GithubRepository,
): ViewModel() {

    private var currentQuery = ""
    private var lastQuery = ""

    val onQueryChangeTextListener = fun(query: String) {
        currentQuery = query
        if (currentQuery.isEmpty())
            clearSearchUserCache()
    }

    private var _onClearedCache = MutableSharedFlow<Boolean>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST,
    )
    val onClearedCache: SharedFlow<Boolean>
        get() = _onClearedCache.asSharedFlow()

    private val _onSearchResultStream = MutableSharedFlow<PagingData<GithubUserEntity>>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST,
    )
    val onSearchResultStream: SharedFlow<PagingData<GithubUserEntity>>
        get() = _onSearchResultStream.asSharedFlow()

    private val _onSearchResultSummaryStream = MutableStateFlow<GithubSearchUserSummaryEntity?>(null)
    val onSearchResultSummaryStream: StateFlow<GithubSearchUserSummaryEntity?>
        get() = _onSearchResultSummaryStream.asStateFlow()

    private var summaryJob: Job? = null
    private var searchJob: Job? = null

    fun updateLikedState(user: GithubUserEntity) {
        viewModelScope.launch {
            repository.updateLikedState(user)
        }
    }

    fun clearSearchUserCache() {
//        if (lastQuery.isEmpty()) return
        searchJob?.cancel()
        summaryJob?.cancel()
        viewModelScope.launch {
            repository.clearSearchUserCache(lastQuery)
            lastQuery = ""
            _onClearedCache.emit(true)
        }
    }

    fun onClickSearchButton() {
        if (currentQuery.isEmpty()) return
        clearSearchUserCache()
        lastQuery = currentQuery
//        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            repository.getSearchUserResultStream(lastQuery).cachedIn(viewModelScope)
//                // viewType 나눌 때 활용하기
//                .map { pagingData ->
//                    pagingData.map { user -> user }
//                    pagingData
//                }
                .collectLatest {
                    _onSearchResultStream.emit(it)
                }
        }
//        summaryJob?.cancel()
        summaryJob = viewModelScope.launch {
            repository.getTotalCountStream(lastQuery).collectLatest {
                _onSearchResultSummaryStream.emit(it)
            }
        }
    }

    companion object {

        const val TAG = "GithubViewModel"
    }
}