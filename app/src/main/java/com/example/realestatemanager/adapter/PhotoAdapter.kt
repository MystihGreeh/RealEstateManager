package com.example.realestatemanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.realestatemanager.R

class PhotoAdapter(private val allPhotos: MutableList<String>, ) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>(){


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val propertyPicture = view.findViewById<ImageView>(R.id.property_photo_item)!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_horizontal_photos, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPhoto = allPhotos[position]
        holder.propertyPicture.setImageURI(currentPhoto.toUri())
        holder.propertyPicture.cropToPadding

    }
    override fun getItemCount(): Int = allPhotos.size

}