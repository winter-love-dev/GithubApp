package com.season.winter.githubapp.appcore.domain.github.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "github_search_user_summary_entity")
data class GithubSearchUserSummaryEntity(

    @PrimaryKey
    @ColumnInfo(name = "query")
    val query: String = "",

    @ColumnInfo(name = "total_count")
    val totalCount: Int = 0,

    @ColumnInfo(name = "total_page")
    val totalPage: Int = 0,
)
