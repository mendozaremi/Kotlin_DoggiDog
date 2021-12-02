package com.remi.doggiedog.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DogEntity::class], version = 1)
abstract class DogRoomDatabase : RoomDatabase() {
    abstract fun dogDao(): DogDao

    companion object {
        @Volatile
        private var INSTANCE: DogRoomDatabase? = null

        fun getDatabase(context: Context): DogRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    DogRoomDatabase::class.java,
                    "dog_database"
                )
                    .allowMainThreadQueries()
                    .build()
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                  INSTANCE = instance

                instance
            }
        }
    }
}