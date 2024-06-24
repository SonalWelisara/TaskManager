package com.sonal.taskmanger

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.sonal.taskmanger.databinding.ActivityUpdateBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Update : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database= Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "Focus_On"
        ).build()
        val pos=intent.getIntExtra("id",-1)
        if(pos!=-1){
            val title=DataObject.getData(pos).title
            val priority=DataObject.getData(pos).priority
            binding.createTitle.setText(title)
            binding.createPriority.setText(priority)


            binding.deleteButton.setOnClickListener{
                DataObject.deleteData(pos)
                GlobalScope.launch {
                    database.dao().deleteTask(
                        Entity(pos+1,title,priority)
                    )
                    for (i in pos until DataObject.getAllData().size) {
                        val item = DataObject.getData(i)
                        database.dao().updateTask(Entity(i + 1, item.title, item.priority))
                    }
                }

                myIntent()
            }

            fun showErrorToast() {
                Toast.makeText(this, "Error: Title and Priority cannot be empty", Toast.LENGTH_SHORT).show()
            }
            fun showInvalidError() {
                Toast.makeText(this, "Error: Invalid Priority", Toast.LENGTH_SHORT).show()
            }


            binding.updateButton.setOnClickListener{
                val titleUp = binding.createTitle.text.toString()
                val priorityUp = binding.createPriority.text.toString()
                if(titleUp.trim{it<=' '}.isNotEmpty()
                    && priorityUp.trim{it<=' '}.isNotEmpty()){

                    if(priorityUp.toLowerCase() == "high"
                        || priorityUp.toLowerCase() == "medium"
                        || priorityUp.toLowerCase() == "low"){

                        GlobalScope.launch {
                            database.dao().updateTask(
                                Entity(pos+1, titleUp, priorityUp)
                            )
                        }
                        DataObject.updateData(
                            pos,
                            titleUp,
                            priorityUp
                        )
                        myIntent()
                    }else{
                        showInvalidError()
                    }

                }else{
                    showErrorToast()
                }
            }
        }
    }

    private fun myIntent(){
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

}