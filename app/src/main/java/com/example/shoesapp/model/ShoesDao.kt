package com.example.shoesapp.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ShoesDao {
    @Query("SELECT * FROM TABLE_SHOES ORDER BY id ASC")
    fun getAllDatos(): LiveData<List<Shoes>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllShoes(listShoes: List<Shoes>)

    @Query("DELETE FROM TABLE_SHOES")
    suspend fun deleteAll()

    @Query("DELETE FROM TABLE_SHOES where id=:id")
    suspend fun deleteUno(id: Int)
}