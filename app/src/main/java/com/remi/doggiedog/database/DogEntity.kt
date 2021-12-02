package com.remi.doggiedog.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Represents a table within the database. Room creates a table for each class that has @Entity
// annotation the fields in the class correspond to the columns in the table. therefore the
// entity classes tend to be small model classes that don't contain any logic
@Entity(tableName = "DogImages")
data class DogEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "url")
    val url: String,
)

