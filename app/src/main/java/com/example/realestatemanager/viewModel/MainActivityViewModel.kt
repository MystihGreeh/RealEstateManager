package com.example.realestatemanager.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.realestatemanager.model.Property
import com.example.realestatemanager.repositories.PropertyRepository

class MainActivityViewModel(private val repository : PropertyRepository) : ViewModel() {

    val allProperties: LiveData<List<Property>> = repository.allProperties



}