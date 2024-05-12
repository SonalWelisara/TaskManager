package com.sonal.taskmanger

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DAO {
    @Insert
    suspend fun insertTask(entity: Entity)

    @Update
    suspend fun updateTask(entity: Entity)

    @Delete
    suspend fun deleteTask(entity: Entity)

    @Query("Delete from Focus_On")
    suspend fun deleteAll()

    @Query("Select * from Focus_On")
    suspend fun getTask():List<CardInfo>
}