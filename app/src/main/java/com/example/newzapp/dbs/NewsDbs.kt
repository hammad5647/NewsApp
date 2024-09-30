package com.example.newzapp.dbs

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newzapp.models.NewsEntity

@Database(entities = [NewsEntity::class], version = 1)
abstract class NewsDbs : RoomDatabase() {

    abstract fun dao(): DAO

    companion object {

        var dbs: NewsDbs? = null

        fun initDatabase(context: Context): NewsDbs {
            if (dbs == null) {
                dbs = Room.databaseBuilder(context, NewsDbs::class.java, "news_db")
                    .allowMainThreadQueries().build()
            }
            return dbs!!
        }
    }
}