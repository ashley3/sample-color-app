package com.example.colors.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ColorDao {
  @Query("SELECT * FROM colors")
  fun getAll(): Flow<List<Color>>

  @Query("SELECT * FROM colors WHERE _id=:id")
  suspend fun getColorById(id : Int) : Color

  @Insert
  suspend fun insert(vararg color: Color)
}