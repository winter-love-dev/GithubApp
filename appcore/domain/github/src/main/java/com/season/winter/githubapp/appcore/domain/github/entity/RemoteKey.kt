package com.season.winter.githubapp.appcore.domain.github.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKey(
    @PrimaryKey
    val label: String,
    val prevKey: Int?,
    val nextKey: Int?
) {

//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "id")
//    var id: Long = 0
}
