package com.season.winter.githubapp.core.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "github_user_entity")
data class GithubUserEntity(

    @ColumnInfo(name = "liked")
    var liked: Boolean = false,

    @ColumnInfo(name = "query")
    var query: String? = null,

    @ColumnInfo(name = "id")
    @field:SerializedName("id")
    val id: Long?,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "sort")
    var sort: Long? = null,

    @ColumnInfo(name = "nickname")
    @field:SerializedName("login") val login: String,

    @field:SerializedName("node_id") val nodeId: String,
    @field:SerializedName("avatar_url") val avatarUrl: String,
    @field:SerializedName("gravatar_id") val gravatarId: String,

    @ColumnInfo(name = "url")
    @field:SerializedName("url") val url: String,

    @field:SerializedName("html_url") val htmlUrl: String,
    @field:SerializedName("followers_url") val followersUrl: String,
    @field:SerializedName("following_url") val followingUrl: String,
    @field:SerializedName("gists_url") val gistsUrl: String,
    @field:SerializedName("starred_url") val starredUrl: String,
    @field:SerializedName("subscriptions_url") val subscriptionsUrl: String,
    @field:SerializedName("organizations_url") val organizationsUrl: String,
    @field:SerializedName("repos_url") val reposUrl: String,
    @field:SerializedName("events_url") val eventsUrl: String,
    @field:SerializedName("received_events_url") val receivedEventsUrl: String,
    @field:SerializedName("type") val type: String,
    @field:SerializedName("site_admin") val siteAdmin: Boolean,
    @field:SerializedName("score") val score: Double,
)/* {

    // GithubUserDiffCallback 상에서 어차피 id 비교를 하기 때문에 지금은 필요 없음.
    // areItemsTheSame 상에서 먼저 거르면 한 단 계 더 빠르긴 할텐데 굳이?
    // 객체의 동등성을 id 값으로 비교
    override fun equals(other: Any?): Boolean {
        return other is GithubUserEntity && id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}*/
