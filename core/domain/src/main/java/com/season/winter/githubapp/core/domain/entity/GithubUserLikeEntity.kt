package com.season.winter.githubapp.core.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "github_user_like_entity")
data class GithubUserLikeEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "liked")
    var liked: Boolean = false,
) {


    // 객체의 동등성을 id 값으로 비교
    override fun equals(other: Any?): Boolean {
        return other is GithubUserLikeEntity && id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
