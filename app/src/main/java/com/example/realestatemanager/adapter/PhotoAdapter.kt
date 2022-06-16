package com.example.realestatemanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.realestatemanager.R
import com.example.realestatemanager.model.PropertyPhoto

class PhotoAdapter(private val allPhotos: MutableList<PropertyPhoto>, ) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>(){


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val propertyPicture = view.findViewById<ImageView>(R.id.property_photo_item)!!
        val propertyPictureDetail= view.findViewById<TextView>(R.id.horizontal_photo_description_textview)!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_horizontal_photos, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        allPhotos
        val currentPhoto = allPhotos[position]
        holder.propertyPicture.setImageURI(currentPhoto.name.toUri())
        holder.propertyPicture.cropToPadding
        holder.propertyPictureDetail.text = currentPhoto.description
    }
    override fun getItemCount(): Int = allPhotos.size

}