package com.example.realestatemanager.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.realestatemanager.R
import com.example.realestatemanager.model.Property

class PropertyAdapter(val context: ListViewFragment, private val allProperties: List<Property>, val propertyClickInterface: PropertyClickInterface, val propertyDeleteInterface: PropertyDeleteInterface) : RecyclerView.Adapter<PropertyAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val propertyTypeOfGood = itemView.findViewById<TextView>(R.id.property_style)
        val propertyCity = itemView.findViewById<TextView>(R.id.property_location)
        val propertyPrice = itemView.findViewById<TextView>(R.id.property_price)
        val propertyOnSale = itemView.findViewById<TextView>(R.id.is_sold)
        val propertyPicture = itemView.findViewById<ImageView>(R.id.property_picture)
        val deleteButton = itemView.findViewById<ImageView>(R.id.delete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.property_rv_items, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val property = allProperties[position]
        holder.propertyTypeOfGood.text = property.typeOfGood
        holder.propertyCity.text = property.city
        holder.propertyPrice.text = property.priceInDollars
        if (property.isSold == false) {
            holder.propertyOnSale.setText(R.string.on_sale)
            holder.propertyOnSale.setTextColor(Color.parseColor("#04711C"))
        } else {
            holder.propertyOnSale.setText(R.string.sold)
            holder.propertyOnSale.setTextColor(Color.parseColor("#C50017"))
        }
        holder.itemView.setOnClickListener {
            propertyClickInterface.onPropertyClick(allProperties[position])
        }
        holder.deleteButton.setOnClickListener{
            propertyDeleteInterface.onDeleteClick(allProperties[position])
        }
        holder.propertyPicture.setImageURI(property.propertyImage.toUri())
        holder.propertyPicture.cropToPadding

    }
        override fun getItemCount(): Int {
            return allProperties.size
        }
    }





interface PropertyClickInterface{
    fun onPropertyClick(property: Property)
}

interface PropertyDeleteInterface{
    fun onDeleteClick(property: Property)
}

