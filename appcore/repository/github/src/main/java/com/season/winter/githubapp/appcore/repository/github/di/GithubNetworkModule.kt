package com.season.winter.githubapp.appcore.repository.github.di

import com.season.winter.githubapp.appcore.domain.github.domain.GithubRestApiService
import com.season.winter.githubapp.appcore.repository.github.network.GithubRestApiManager
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
    fun provideUnsplashService(): GithubRestApiService {
        return GithubRestApiManager.create()
    }
}
