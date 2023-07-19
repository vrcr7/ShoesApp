package com.example.shoesapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Shoes::class), version = 1, exportSchema = false)
abstract class ShoesRoomDatabase : RoomDatabase() {
    abstract fun ShoesDao(): ShoesDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ShoesRoomDatabase? = null

        fun getDatabase(context: Context): ShoesRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShoesRoomDatabase::class.java,
                    "shoes_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}