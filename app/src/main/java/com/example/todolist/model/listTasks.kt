package com.example.todolist.model

data class listTasks(
    var title: String, var duedate: String,
    var description: String?, var check:Boolean=false) {
}
