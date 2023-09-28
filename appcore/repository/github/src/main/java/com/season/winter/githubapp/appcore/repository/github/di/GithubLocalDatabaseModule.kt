package com.season.winter.githubapp.appcore.repository.github.di

import android.content.Context
import com.season.winter.githubapp.appcore.domain.github.GithubLocalDao
import com.season.winter.githubapp.appcore.domain.github.RemoteKeyDao
import com.season.winter.githubapp.appcore.repository.github.database.GithubLocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GithubLocalDatabaseModule {

    @Singleton
    @Provides
    fun provideGithubLocalDatabase(
        @ApplicationContext context: Context
    ): GithubLocalDatabase {
        return GithubLocalDatabase.getInstance(context)
    }

    @Provides
    fun provideRemoteConfigDao(database: GithubLocalDatabase): GithubLocalDao {
        return database.githubDao()
    }

    @Provides
    fun provideRemoteKeyDao(database: GithubLocalDatabase): RemoteKeyDao {
        return database.remoteKeyDao()
    }

}