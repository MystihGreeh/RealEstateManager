package com.example.realestatemanager.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.realestatemanager.R
import com.example.realestatemanager.model.Property

class PropertyAdapter (private val allProperties: List<Property>, val propertyClickInterface: PropertyClickInterface) : RecyclerView.Adapter<PropertyAdapter.ViewHolder>() {



    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val propertyTypeOfGood = itemView.findViewById<TextView>(R.id.property_style)
        val propertyCity = itemView.findViewById<TextView>(R.id.property_location)
        val propertyPrice = itemView.findViewById<TextView>(R.id.property_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.property_rv_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val property = allProperties[position]
        holder.propertyTypeOfGood.text = property.typeOfGood
        holder.propertyCity.text =property.city
        holder.propertyPrice.text = property.priceInDollars

        holder.itemView.setOnClickListener {
            propertyClickInterface.onPropertyClick(allProperties[position])
        }
    }

    override fun getItemCount(): Int {
        return allProperties.size
    }
}

interface PropertyClickInterface{
    fun onPropertyClick(property: Property)
}
