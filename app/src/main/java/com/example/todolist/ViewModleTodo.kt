package com.example.todolist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.data.datasource
import com.example.todolist.model.listTasks

class ViewModleTodo : ViewModel() {

    val title = MutableLiveData<String>("")
    val data = MutableLiveData<String>("")
    val subTitle = MutableLiveData<String>("")

    fun NewTask(){
        datasource().loadTasks().add(listTasks(title.value.toString(),subTitle.value.toString(),data.value.toString()))
    }
}