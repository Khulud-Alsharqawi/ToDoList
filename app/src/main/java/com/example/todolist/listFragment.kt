package com.example.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.Adapter.toDoListAdapter
import com.example.todolist.data.datasource
import com.example.todolist.data.myDB
import com.example.todolist.databinding.FragmentListBinding


class listFragment : Fragment() {
        var binding:FragmentListBinding?= null
    lateinit var recyclerView:RecyclerView
    val dataset= datasource().loadTasks()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        binding!!.lifecycleOwner = viewLifecycleOwner
        val view = binding?.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       recyclerView= binding?.recyclerview!!
        binding?.recyclerview?.adapter=toDoListAdapter(dataset)
        binding?.addbutton?.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(listFragmentDirections.actionListToAdd(addtitle = ""))
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}