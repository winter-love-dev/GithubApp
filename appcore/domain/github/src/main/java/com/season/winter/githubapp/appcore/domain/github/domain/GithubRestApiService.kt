package com.season.winter.githubapp.appcore.domain.github.domain

import com.season.winter.githubapp.appcore.domain.github.entity.GithubSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubRestApiService {

    @GET("search/users")
    suspend fun searchUser(
        @Query("q") name: String,
        @Query("per_page") resultCountLimit: Int,
        @Query("page") page: Int,
    ): GithubSearchResponse

}