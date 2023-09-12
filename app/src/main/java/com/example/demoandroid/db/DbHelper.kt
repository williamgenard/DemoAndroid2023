package com.example.demoandroid.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.demoandroid.db.daos.BookDao
import com.example.demoandroid.db.entities.Book

@Database(entities = [Book::class], version = 1)
abstract class DbHelper : RoomDatabase() {
    //DAO
    abstract fun bookDao() : BookDao
    // Statique
    companion object {
        const val DB_NAME = "room_database"

        private var instance : DbHelper? = null

        fun instance(context : Context) : DbHelper {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    DbHelper::class.java,
                    DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }
}