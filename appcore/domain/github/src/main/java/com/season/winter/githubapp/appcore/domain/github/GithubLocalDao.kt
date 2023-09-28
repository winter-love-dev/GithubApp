package com.season.winter.githubapp.appcore.domain.github

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.season.winter.githubapp.appcore.domain.github.entity.GithubSearchUserSummaryEntity
import com.season.winter.githubapp.appcore.domain.github.entity.GithubUserEntity
import com.season.winter.githubapp.appcore.domain.github.entity.GithubUserLikeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GithubLocalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateLikedState(user: GithubUserLikeEntity)

    @Query("SELECT * FROM github_user_like_entity WHERE id IN (:users)")
    suspend fun checkLiked(users: List<Long>): List<GithubUserLikeEntity>?

    @Query("SELECT * FROM github_user_entity") //  ORDER BY sort ASC
    suspend fun checkCachedUsers(): List<GithubUserEntity>

    // 검색 결과 totalCount 을 쉽게 방출할 방법을 못 찾아서 Room으로 대체함
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchSummary(entity: GithubSearchUserSummaryEntity)

    @Query("SELECT * FROM github_search_user_summary_entity WHERE `query` IN (:query)")
    suspend fun checkSearchSummary(query: String): GithubSearchUserSummaryEntity

    @Query("SELECT * FROM github_search_user_summary_entity WHERE `query` IN (:query)")
    fun checkSearchSummaryStream(query: String): Flow<GithubSearchUserSummaryEntity?>

    @Query("DELETE FROM github_search_user_summary_entity WHERE `query` IN(:query)")
    suspend fun deleteSummary(query: String)

    @Query("DELETE FROM github_search_user_summary_entity")
    suspend fun clearAllSearchUserSummary()


    // 페이징 캐싱 관련 쿼리 추가
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserAll(users: List<GithubUserEntity>)

//    @Query("SELECT * FROM github_user_entity WHERE id LIKE (:users)") // ORDER BY sort ASC
//    fun userPagingSource(users: List<Long>): PagingSource<Int, GithubUserEntity>

    @Query("SELECT * FROM github_user_entity WHERE `query` IN (:query)") //  ORDER BY sort ASC
    fun userPagingSource(query: String): PagingSource<Int, GithubUserEntity>

    @Query("SELECT * FROM github_user_entity WHERE url LIKE :query") // ORDER BY sort ASC
    fun userPagingData(query: String): List<GithubUserEntity>

    @Query("DELETE FROM github_user_entity  WHERE id IN (:users)")
    suspend fun deleteUserUseId(users: List<Long>)

    @Query("DELETE FROM github_user_entity WHERE `query` IN (:query)")
    suspend fun deleteUserUseQuery(query: String)

    @Query("DELETE FROM github_user_entity")
    suspend fun clearAllSearchUser()

}