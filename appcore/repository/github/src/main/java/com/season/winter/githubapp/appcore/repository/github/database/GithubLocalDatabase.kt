package com.season.winter.githubapp.appcore.repository.github.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.season.winter.githubapp.appcore.domain.github.GithubLocalDao
import com.season.winter.githubapp.appcore.domain.github.RemoteKeyDao
import com.season.winter.githubapp.appcore.domain.github.entity.GithubSearchUserSummaryEntity
import com.season.winter.githubapp.appcore.domain.github.entity.GithubUserEntity
import com.season.winter.githubapp.appcore.domain.github.entity.GithubUserLikeEntity
import com.season.winter.githubapp.appcore.domain.github.entity.RemoteKey
import com.season.winter.githubapp.appcore.repository.github.constants.DatabaseName_Github

@Database(
    entities = [
        GithubUserEntity::class,
        GithubUserLikeEntity::class,
        GithubSearchUserSummaryEntity::class,
        RemoteKey::class,
    ],
    version = 1,
    exportSchema = false
)
//@TypeConverters(Converters::class)
abstract class GithubLocalDatabase: RoomDatabase() {

    abstract fun githubDao(): GithubLocalDao
//    abstract fun remoteKeyDao(): GithubUserRemoteKeyDao
    abstract fun remoteKeyDao(): RemoteKeyDao

    companion object {

        @Volatile private var instance: GithubLocalDatabase? = null

        fun getInstance(context: Context): GithubLocalDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): GithubLocalDatabase {
            return Room.databaseBuilder(
                context,
                GithubLocalDatabase::class.java,
                DatabaseName_Github
            )
                .addCallback(
                object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // some worker logic ...
                    }
                }
            )
            .build()
        }
    }
}