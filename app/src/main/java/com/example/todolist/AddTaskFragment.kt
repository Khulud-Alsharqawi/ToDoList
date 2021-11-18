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
import com.example.todolist.databinding.FragmentAddTaskBinding


class AddTaskFragment : Fragment() {
    var binding:FragmentAddTaskBinding?= null
    lateinit var recyclerView: RecyclerView
    val dataset= datasource().loadTasks()
        val shareViewModel :ViewModleTodo by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentAddTaskBinding.inflate(inflater,container,false)
        binding!!.lifecycleOwner=viewLifecycleOwner
        val view= binding?.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    binding.apply {
        binding?.savebutton?.setOnClickListener{
           findNavController().navigate(R.id.action_add_to_list)


    }
//        binding?.savebutton?.setOnClickListener{
//            Toast.makeText(this.requireContext(), "Save", Toast.LENGTH_SHORT).show()
//            findNavController().navigate(R.id.action_addTaskFragment_to_listFragment)

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }

    fun newTask(){
        shareViewModel.NewTask()
        findNavController().navigate(R.id.action_addTaskFragment_to_listFragment)
    }

}