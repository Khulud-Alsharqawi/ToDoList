package com.example.todolist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.data.datasource
import com.example.todolist.databinding.FragmentAddTaskBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*


class AddTaskFragment : Fragment() {


    var binding: FragmentAddTaskBinding? = null
    var isDatePass = false
    var dateInMelliSecond = 0L

    val shareViewModel: ViewModleTodo by viewModels()

    // object of view model that connects with the fragment XML
       override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        binding!!.lifecycleOwner = viewLifecycleOwner
        val view = binding?.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = shareViewModel
            addTask = this@AddTaskFragment

        }

        binding?.savebutton?.setOnClickListener {
           compTime()
            if(isDatePass == true){
                Navigation.findNavController(view).navigate(AddTaskFragmentDirections.actionAddToList())
                shareViewModel.NewTask()
            } else {
               Toast.makeText(this.requireContext(),"enter right date",Toast.LENGTH_SHORT).show()
            }
        }
    }
//-----------------------------------------------------
    fun pickDate() {

        // fixed form MaterialDatePicker
        val builder = MaterialDatePicker.Builder.datePicker()
        val picker = builder.build()
        picker.show(requireFragmentManager(), picker.toString())
        picker.addOnNegativeButtonClickListener {
        }
        picker.addOnPositiveButtonClickListener {

            shareViewModel.formatDate(it)        }
    }

    fun compTime(){
        var currentDate= Date()
        val formatter = SimpleDateFormat("yyyy-MM-dd",Locale.US)
        var taskTime=formatter.parse(shareViewModel.vmData.value.toString())
        println(taskTime)
        println("current = $currentDate")

        if (taskTime.before(currentDate)){
            Toast.makeText(this.requireContext(),"the time you choose is not allowed",Toast.LENGTH_LONG).show()
        }else{
            isDatePass=true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null    }
}


