package com.example.realestatemanager.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.realestatemanager.R
import com.example.realestatemanager.model.Property
import java.util.*

class PropertyAdapter (private val layoutId: Int) : RecyclerView.Adapter<PropertyAdapter.ViewHolder>() {


    private val allProperty = ArrayList<Property>()

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val propertyDescriptionTV = itemView.findViewById<TextView>(R.id.property_location)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.property_rv_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.propertyDescriptionTV.setText(allProperty.get(position).city)
    }

    override fun getItemCount(): Int = 5

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Property>){
        allProperty.clear()
        allProperty.addAll(newList)
        notifyDataSetChanged()
    }


}