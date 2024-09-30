package com.example.newzapp.dbs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.newzapp.models.NewsEntity

@Dao
interface DAO {
    @Insert
    fun insertData(entity: NewsEntity)

    @Delete
    fun deleteData(entity: NewsEntity)

    @Query("SELECT * FROM NewsModel")
    fun readData(): MutableList<NewsEntity>
}