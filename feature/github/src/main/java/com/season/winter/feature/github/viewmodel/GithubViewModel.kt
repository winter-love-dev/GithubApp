package com.season.winter.feature.github.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.season.winter.githubapp.appcore.domain.github.GithubRestApiService
import com.season.winter.githubapp.appcore.domain.github.entity.GithubSearchResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
    private val service: GithubRestApiService,
): ViewModel() {

    private val _onSearchUserResult = MutableSharedFlow<GithubSearchResponse>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    val onSearchUserResult: SharedFlow<GithubSearchResponse>
        get() = _onSearchUserResult.asSharedFlow()

    suspend fun callTest(): GithubSearchResponse {
        return service.searchUser("winter-love", 20, 1)
    }

    fun searchUser(query: String) {
        viewModelScope.launch{
            val result = service.searchUser(query, 30, 1)
            _onSearchUserResult.tryEmit(result)
        }
    }

    companion object {

        const val TAG = "GithubViewModel"
    }
}