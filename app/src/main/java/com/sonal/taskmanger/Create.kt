package com.sonal.taskmanger

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.sonal.taskmanger.databinding.ActivityCreateBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Create : AppCompatActivity() {
    private lateinit var binding: ActivityCreateBinding
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database=Room.databaseBuilder(
            applicationContext,myDatabase::class.java,"Focus_On"
        ).build()

        fun showEmptyError() {
            Toast.makeText(this, "Error: Title and Priority cannot be empty", Toast.LENGTH_SHORT).show()
        }

        fun showInvalidError() {
            Toast.makeText(this, "Error: Invalid Priority", Toast.LENGTH_SHORT).show()
        }

        binding.saveButton.setOnClickListener{
            var title=binding.createTitle.text.toString()
            var priority=binding.createPriority.text.toString()
            if(title.trim{it<=' '}.isNotEmpty() && priority.trim{it<=' '}.isNotEmpty()){

                if(priority.toLowerCase() == "high" || priority.toLowerCase() == "medium"|| priority.toLowerCase() == "low"){
                    DataObject.setData(title,priority)
                    GlobalScope.launch {
                        database.dao().insertTask(Entity(0,title,priority))
                        //Log.i("harsh", database.dao().getTask().toString())
                    }
                    val intent= Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    showInvalidError()
                }


            }else{
                showEmptyError()
            }
        }
    }
}