package com.season.winter.githubapp.core.data.di

import com.season.winter.githubapp.core.data.GithubRepositoryImpl
import com.season.winter.githubapp.core.data.database.GithubLocalDatabase
import com.season.winter.githubapp.core.domain.GithubApi
import com.season.winter.githubapp.core.domain.GithubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GithubRepositoryModule {

    @Singleton
    @Provides
    fun provideGithubRepository(
        remoteDao: GithubApi,
        database: GithubLocalDatabase,
    ): GithubRepository {
        return GithubRepositoryImpl(
            remoteDao,
            database,
        )
    }
}