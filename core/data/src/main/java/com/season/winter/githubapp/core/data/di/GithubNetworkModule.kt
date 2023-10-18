package com.season.winter.githubapp.core.data.di

import com.season.winter.githubapp.core.data.network.GithubRestApiManager
import com.season.winter.githubapp.core.domain.GithubApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideGithubApiService(): GithubApi {
        return GithubRestApiManager.create()
    }
}
