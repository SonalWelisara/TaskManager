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

    @Query("Delete from focus_on")
    suspend fun deleteAll()

    @Query("Select * from focus_on")
    suspend fun getTask():List<CardInfo>
}