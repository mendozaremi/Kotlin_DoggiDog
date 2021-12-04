package com.remi.doggiedog.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// Dao stores crud operations
@Dao
interface DogDao {

    @Insert
    suspend fun addDogImage(dogEntity: DogEntity)

    @Query("SELECT * from DogImages")
    fun getAllDogImages(): Flow<List<DogEntity>>

    @Query("DELETE from DogImages where id=(select max(id)-1 from DogImages)")
    suspend fun deleteDog()

    //    @Query("SELECT * FROM DogImages ORDER BY id DESC LIMIT 1")
//    fun getMostRecentlyAddDog() : DogEntity

}