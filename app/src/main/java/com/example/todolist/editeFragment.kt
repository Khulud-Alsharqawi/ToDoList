package com.example.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.data.datasource
import com.example.todolist.databinding.FragmentEditeBinding
import com.example.todolist.databinding.FragmentEditeBindingImpl

class editeFragment : Fragment() {

    var binding: FragmentEditeBinding?=null

    lateinit var recyclerView: RecyclerView

    val dataset= datasource().loadTasks()
    val shareViewModel : ViewModleTodo by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentEditeBinding.inflate(inflater,container,false)
//        binding!!.lifecycleOwner=viewLifecycleOwner


        val view= binding?.root

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            binding?.updatebutton?.setOnClickListener {
                findNavController().navigate(R.id.action_edit_to_list)

            }

            lifecycleOwner=viewLifecycleOwner
            viewModel= ViewModleTodo()

//            updateTask=this@editeFragment

        }
    }
        override fun onDestroy() {
            super.onDestroy()
            binding=null
        }

    fun updateTask(){
        shareViewModel.NewTask()
        findNavController().navigate(R.id.action_edit_to_list)
    }

}