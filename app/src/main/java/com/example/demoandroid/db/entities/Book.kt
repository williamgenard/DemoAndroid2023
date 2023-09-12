package com.example.demoandroid.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book (
    var title: String,
    var description: String,

    @PrimaryKey(autoGenerate = true) var id : Long? = null
)