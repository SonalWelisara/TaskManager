package com.sonal.taskmanger


object DataObject {
    var listData= mutableListOf<CardInfo>()

    fun setData(title: String,priority:String){
        listData.add(CardInfo(title,priority))
    }

    fun getAllData():List<CardInfo>{
        return listData
    }

    fun deleteAll(){
        listData.clear()
    }

    fun getData(pos:Int): CardInfo {
        return listData[pos]
    }

    fun deleteData(pos:Int){
        listData.removeAt(pos)
    }

    fun updateData(pos:Int,title: String, priority: String){
        listData[pos].title=title
        listData[pos].priority=priority
    }

}