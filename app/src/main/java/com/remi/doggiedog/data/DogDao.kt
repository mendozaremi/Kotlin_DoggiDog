package com.remi.doggiedog.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

// Dao stores crud operations
@Dao
interface DogDao {

    @Query("SELECT * from dog ORDER BY name ASC")
    fun getPrevDog(): Flow<Dog>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dog: Dog)

    @Update
    suspend fun update(dog: Dog)

    @Delete
    suspend fun delete(dog: Dog)
}