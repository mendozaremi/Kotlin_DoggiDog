package com.remi.doggiedog.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Represents a table within the database. Room creates a table for each class that has @Entity
// annotation the fields in the class correspond to the columns in the table. therefore the
// entity classes tend to be small model classes that don't contain any logic
@Entity(tableName = "previousDog")
data class Dog(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "url")
    val url: String,
)

