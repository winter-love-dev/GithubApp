package com.season.winter.githubapp.core.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.season.winter.githubapp.core.data.constants.DatabaseName_Github
import com.season.winter.githubapp.core.domain.GithubLocalDao
import com.season.winter.githubapp.core.domain.RemoteKeyDao
import com.season.winter.githubapp.core.domain.entity.GithubSearchUserSummaryEntity
import com.season.winter.githubapp.core.domain.entity.GithubUserEntity
import com.season.winter.githubapp.core.domain.entity.GithubUserLikeEntity
import com.season.winter.githubapp.core.domain.entity.RemoteKey

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