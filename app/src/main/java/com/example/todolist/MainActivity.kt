package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.Adapter.toDoListAdapter
import com.example.todolist.R
import com.example.todolist.data.datasource
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.model.listTasks

class MainActivity : AppCompatActivity(R.layout.fragment_list) {
    private lateinit var binding: ActivityMainBinding
private lateinit var navController :NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initialize date
        val myData= datasource().loadTasks()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_graph) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)

//         binding.taskRv.adapter = toDoListAdapter(myData )
//
//        binding.taskRv.setHasFixedSize(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}



