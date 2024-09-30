package com.example.newzapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NewsModel")
data class NewsEntity(
    @ColumnInfo
    var newsTitle: String? = null,
    @PrimaryKey(true)
    var newsId: Int = 0,
    @ColumnInfo
    var newsDescription: String? = null,
    @ColumnInfo
    var newsImage: String? = null,
    @ColumnInfo
    var newsPublishDate: String? = null,
    @ColumnInfo
    var newsCountry: String? = null,
    @ColumnInfo
    var newsAuthor: String? = null,
    @ColumnInfo
    var newsSource: String? = null,
    @ColumnInfo
    var newsCategory: String? = null,


)
