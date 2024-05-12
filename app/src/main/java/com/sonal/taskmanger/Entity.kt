package com.sonal.taskmanger

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Focus_On")
class Entity(
    @PrimaryKey(autoGenerate = true)
             var id:Int,
             var title:String,
             var priority:String) {



}