package com.example.todolist

import android.text.Editable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.data.datasource
import com.example.todolist.data.myDB
import com.example.todolist.model.listTasks
import org.w3c.dom.Text

class ViewModleTodo : ViewModel() {

    var vmTitle = MutableLiveData<String>("")
    var vmData = MutableLiveData<String>("")
    var vmSubTitle = MutableLiveData<String>("")

    fun NewTask(){
        Log.e("TAG","add date:${vmData.value}")
        var dsObj = datasource()
        dsObj.addTask(listTasks(vmTitle.value.toString(),
            vmData.value.toString()
            ,vmSubTitle.value.toString()
            ,false))

//  datasource().loadTasks().add(listTasks(title.value.toString(),subTitle.value.toString(),data.value.toString()))
    }
    fun setdate(date:String){

    }

//    fun grabTask(index:Int ){
//        var item= myDB.get(index)
//        vmTitle.value=item.title
//        vmSubTitle.value=item.description
//        vmData.value=item.duedate
//    }
    fun grabTask(title:String ){
        var item= myDB.find { it.title.equals(title,ignoreCase = true) }
        vmTitle.value=item?.title
        vmSubTitle.value=item?.description
        vmData.value=item?.duedate



    }


    fun edit(title:String) {
        var index = myDB.indexOfFirst { it.title.equals(title, ignoreCase = true) }
        myDB[index].title = vmTitle.value!!
        myDB[index].description = vmSubTitle.value!!
        myDB[index].duedate = vmData.value!!

    }
}