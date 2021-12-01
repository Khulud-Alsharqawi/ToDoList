package com.example.todolist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.data.datasource
import com.example.todolist.data.myDB
import com.example.todolist.model.listTasks
import java.text.SimpleDateFormat
import java.util.*

class ViewModleTodo : ViewModel() {

    var vmTitle = MutableLiveData<String>()
    var vmData = MutableLiveData<String>()
    var vmSubTitle = MutableLiveData<String>()
    var vmcreationDay= MutableLiveData<String>()

    fun NewTask(){
        Log.e("TAG","add date:${vmData.value}")
        var dsObj = datasource()
        dsObj.addTask(listTasks(vmTitle.value.toString(),
            vmData.value.toString()
            ,vmSubTitle.value.toString()
            ,false
            ,vmcreationDay.value.toString()))
    }

    fun grabTask(title:String ){
        var item= myDB.find { it.title.equals(title,ignoreCase = true) }
        vmTitle.value=item?.title
        vmSubTitle.value=item?.description
        vmData.value=item?.duedate
    }


    fun edit(title:String) {
      //  Log.e("TAG", "edit 1 : ${vmData.value!!}", )
        var index = myDB.indexOfFirst { it.title.equals(title, ignoreCase = true) }
        myDB[index].title = vmTitle.value!!
        myDB[index].description = vmSubTitle.value!!
      //  Log.e("TAG", "edit 2 : ${vmData.value!!}", )
        myDB[index].duedate = vmData.value!!

    }
    fun formatDate(date: Long) {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val selectedDate = date
        vmData.value = formatter.format(selectedDate).toString()
        val calendar = Calendar.getInstance()
        vmcreationDay.value = formatter.format(calendar.time)
    }




}