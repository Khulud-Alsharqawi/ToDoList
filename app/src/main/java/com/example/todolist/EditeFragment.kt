package com.example.todolist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    var binding: FragmentEditeBinding? = null
    var isDatePass: Boolean = false


    val shareViewModel: ViewModleTodo by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditeBinding.inflate(inflater, container, false)
        binding!!.lifecycleOwner = viewLifecycleOwner
        val view = binding?.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = shareViewModel
            updateTask = this@EditeFragment
        }
        var orignailTitle: String = ""

        binding?.updateButton?.setOnClickListener {
            Log.e("TAG", "before comp ${isDatePass}")


            compTime()
            Log.e("TAG", "AFTER comp ${isDatePass}")
            if (!isDatePass) {
                Log.e("TAG", "vmdate${shareViewModel.vmData}")
                Log.e("TAG", "after true${isDatePass}")

                Toast.makeText(this.requireContext(), " date", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(view).navigate(EditeFragmentDirections.actionEToList())
                shareViewModel.edit(orignailTitle)

            } else {
                Toast.makeText(this.requireContext(), ">_<", Toast.LENGTH_SHORT).show()
            }
        }

// update task
        arguments?.let {
            orignailTitle = it.getString("title")!!
        }
        shareViewModel.grabTask(orignailTitle)

    }

    //----------------------------------------------------------------------
    fun pickDate() {
        // fixed form
        val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build()

        datePicker.show(parentFragmentManager, "DatePicker")
        datePicker.addOnPositiveButtonClickListener {
            cal = readDate(it, "dd-MM-yyyy")
            binding?.calendertxtupdate?.setText(cal)
        }

    }

    fun compTime() {
        var currentDate = Date()
        val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)
        println(shareViewModel.vmData.value.toString())

        var taskTime = formatter.parse(shareViewModel.vmData.value.toString())
        println(taskTime)
        println("currentDate = $currentDate")
        if (taskTime.before(currentDate)) {
            Log.e("TAG", "isDate:${isDatePass}")
            Toast.makeText(
                this.requireContext(),
                "the time you choose is not allowed",
                Toast.LENGTH_LONG
            ).show()

            isDatePass = true
        } else {
            Log.e("TAG", "isDate00: else")

            Toast.makeText(this.requireContext(), "allowed", Toast.LENGTH_LONG).show()


        }
    }
    // fixed form

    private fun readDate(setDate: Long, datePattern: String): String {
        val format = SimpleDateFormat(datePattern, Locale.getDefault())
        return format.format(Date(setDate))

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}