package com.example.demoandroid.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.demoandroid.db.entities.Book

@Dao
interface BookDao {
    @Query("SELECT * FROM books")
    suspend fun findAll() : List<Book>
    @Query("SELECT * FROM books WHERE id = :id")
    suspend fun findById(id: Long) : Book
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(book : Book) : Long
    @Update
    suspend fun update(book: Book)
}