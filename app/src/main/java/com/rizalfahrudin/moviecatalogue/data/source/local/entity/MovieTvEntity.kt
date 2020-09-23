package com.rizalfahrudin.moviecatalogue.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "movie_tv_entities")
data class MovieTvEntity(
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "id")
    var id: Int?,

    @ColumnInfo(name = "title")
    var title: String?,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "image")
    var image: String?,

    @ColumnInfo(name = "type")
    var type: Int?,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
)