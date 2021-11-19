package com.example.todolist.Adapter

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.listFragmentDirections
import com.example.todolist.model.listTasks

class toDoListAdapter( var dataset: MutableList<listTasks>)
    :RecyclerView.Adapter<toDoListAdapter.ItemViewHolder>(){


    class ItemViewHolder(view:View):RecyclerView.ViewHolder(view) {
    // the id of the text in the todolistmain.xml
        val titelView:TextView=view.findViewById(R.id.tittelText)

        val dateView:TextView=view.findViewById(R.id.deudatetext)
        val checkview: CheckBox =view.findViewById(R.id.checkBox)
        val deletitem: ImageButton=view.findViewById(R.id.deletButton) //on click listener ->task delete
        var edit :ImageButton=view.findViewById(R.id.editbutton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
    val adapterLayout= LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ItemViewHolder(adapterLayout)
    }
    private fun toggleStrikeThrough(titletask:TextView , checkBox: Boolean){
        if (checkBox){
            titletask.paintFlags= titletask.paintFlags or STRIKE_THRU_TEXT_FLAG

        }else{
            titletask.paintFlags=titletask.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
val item= dataset[position]
    holder.titelView.text=item.title
        holder.dateView.text=item.duedate
        holder.deletitem.setOnClickListener {
            dataset.removeAt(position)
            notifyDataSetChanged()
        }
        holder.checkview.isChecked=item.check
        toggleStrikeThrough(holder.titelView  , item.check)
        holder.checkview.setOnCheckedChangeListener { _, isChecked ->
            toggleStrikeThrough(holder.titelView , isChecked)
        }
// =========================================================================================
        holder.edit.setOnClickListener {

            var action =listFragmentDirections.actionListToEdit(item.title)
            holder.edit.findNavController().navigate(action)

        }
    }

    override fun getItemCount(): Int {
        return dataset.size

    }
 }


