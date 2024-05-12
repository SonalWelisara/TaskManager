package com.sonal.taskmanger

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.sonal.taskmanger.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database:myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database=Room.databaseBuilder(
            applicationContext,myDatabase::class.java,"Focus_On"
        ).build()
        binding.add.setOnClickListener{
            val intent=Intent(this,Create::class.java)
            startActivity(intent)
        }
        binding.deleteAll.setOnClickListener{
            DataObject.deleteAll()
            GlobalScope.launch {
                database.dao().deleteAll()
            }
            setRecycler()
        }

        setRecycler()

    }
    fun setRecycler(){
        binding.recyclerView.adapter=Adapter(DataObject.getAllData())
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
    }
}