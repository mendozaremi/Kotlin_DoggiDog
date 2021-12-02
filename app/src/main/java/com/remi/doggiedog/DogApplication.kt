package com.remi.doggiedog

import android.app.Application
import com.remi.doggiedog.database.DogRoomDatabase

class DogApplication : Application() {
    val database: DogRoomDatabase by lazy { DogRoomDatabase.getDatabase(this) }
}