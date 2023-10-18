package com.season.winter.githubapp.core.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "github_user_remote_keys")
data class GithubUserRemoteKeyEntity(
    val label: String,
    val nextKey: Int?
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}