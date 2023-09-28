package com.season.winter.githubapp.appcore.domain.github

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.season.winter.githubapp.appcore.domain.github.entity.GithubUserRemoteKeyEntity

@Dao
interface GithubUserRemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: GithubUserRemoteKeyEntity)

    @Query("SELECT * FROM github_user_remote_keys WHERE label = :query")
    suspend fun remoteKeyByQuery(query: String): GithubUserRemoteKeyEntity

    @Query("DELETE FROM github_user_remote_keys WHERE label = :query")
    suspend fun deleteByQuery(query: String)
}