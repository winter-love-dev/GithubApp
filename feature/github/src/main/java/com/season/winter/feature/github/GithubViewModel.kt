package com.season.winter.feature.github

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.season.winter.githubapp.appcore.domain.github.domain.GithubRestApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
    private val service: GithubRestApiService
): ViewModel(){

    fun callTest() {
        viewModelScope.launch {
            val result = service.searchUser("winter", 20, 1)
            Log.e(TAG, "callTest: $result", )
        }
    }

    companion object {

        const val TAG = "GithubViewModel"
    }
}