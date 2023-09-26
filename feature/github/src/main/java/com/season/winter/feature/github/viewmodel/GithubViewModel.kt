package com.season.winter.feature.github.viewmodel

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.season.winter.core.common.fragment.util.FragmentService
import com.season.winter.feature.github.fragment.GithubUserSearchFragment
import com.season.winter.githubapp.appcore.domain.github.GithubRestApiService
import com.season.winter.githubapp.appcore.domain.github.entity.GithubSearchResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
    private val service: GithubRestApiService,
    private val fragmentService: FragmentService
): ViewModel(){

    suspend fun callTest(): GithubSearchResponse {
        return service.searchUser("winter-love", 20, 1)
    }

    fun startFragment(
        frameBase: View,
        fragment: Fragment = GithubUserSearchFragment()
    ) {
        fragmentService.startFragment(frameBase, fragment)
    }

    companion object {

        const val TAG = "GithubViewModel"
    }
}