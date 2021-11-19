package com.example.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.todolist.databinding.FragmentEditeBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*


class EditeFragment : Fragment() {


    var cal = ""
    var binding: FragmentEditeBinding?=null

    val shareViewModel : ViewModleTodo by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentEditeBinding.inflate(inflater,container,false)
        binding!!.lifecycleOwner=viewLifecycleOwner
        val view= binding?.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel=shareViewModel
            updateTask=this@EditeFragment
        }

       var orignailTitle:String=""
        arguments?.let {
            orignailTitle=it.getString("title")!!
        }
        shareViewModel.grabTask(orignailTitle)

        binding?.updateButton?.setOnClickListener { view:View ->
            Navigation.findNavController(view).navigate(EditeFragmentDirections.actionEToList())
            shareViewModel.edit(orignailTitle)

        }
    }

    fun pickDate() {
        // fixed form
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

        datePicker.show(parentFragmentManager, "DatePicker")
        datePicker.addOnPositiveButtonClickListener {
            cal = readDate(it, "dd/MM/yyyy")
            binding?.calendertxtupdate?.setText(cal)
        }

    }
    // fixed form

    private fun readDate(setDate: Long, datePattern: String): String {
        val format = SimpleDateFormat(datePattern, Locale.getDefault())
        return format.format(Date(setDate))

    }

        override fun onDestroy() {
            super.onDestroy()
            binding=null
        }


}