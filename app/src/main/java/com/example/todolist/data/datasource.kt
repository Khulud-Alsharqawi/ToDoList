package com.example.todolist.data

import com.example.todolist.model.listTasks





class datasource{

    fun loadTasks():MutableList<listTasks>
    {
        return mutableListOf<listTasks>(

        listTasks("super market","bring vegetables","15/11/2021"),
        listTasks("library","bring charger","23/5/2021"),
        listTasks("me time","drawing activity","3/4/2021")
    )

    }
}

//val experament:MutableList<listTasks> = mutableListOf()