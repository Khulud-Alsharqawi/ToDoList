package com.example.todolist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.data.datasource
import com.example.todolist.databinding.FragmentAddTaskBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*


class AddTaskFragment : Fragment() {

    var cal = ""
    var binding: FragmentAddTaskBinding? = null
//    lateinit var recyclerView: RecyclerView

    val shareViewModel: ViewModleTodo by viewModels() // object of view model that connects with the fragment XML
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
//        var cindex = 0
//        arguments?.let {
//            cindex = it?.getInt("index")
//        }

        binding?.savebutton?.setOnClickListener {
            findNavController().navigate(AddTaskFragmentDirections.actionAddToList())
            shareViewModel.NewTask()
        }


    }

    fun pickDate() {

        // fixed form MaterialDatePicker
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

        datePicker.show(parentFragmentManager, "DatePicker")
        datePicker.addOnPositiveButtonClickListener {
            cal= readDate(it, "dd/MM/yyyy")
        binding?.calendertxt?.setText(cal)
            Log.e("TAG","add date:${cal}")
        }

    }
    // fixed form MaterialDatePicker

    private fun readDate(setDate: Long, datePattern: String): String {
        val format = SimpleDateFormat(datePattern, Locale.getDefault())
        return format.format(Date(setDate))

    }
        override fun onDestroy() {
            super.onDestroy()
            binding = null
        }


    }
