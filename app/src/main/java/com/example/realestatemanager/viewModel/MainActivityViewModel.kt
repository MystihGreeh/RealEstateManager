package com.example.realestatemanager.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realestatemanager.model.Property
import com.example.realestatemanager.model.PropertyPhoto
import com.example.realestatemanager.repositories.PhotoPropertyRepository
import com.example.realestatemanager.repositories.PropertyRepository
import com.example.realestatemanager.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repository : PropertyRepository, private val photoRepository: PhotoPropertyRepository) : ViewModel() {

    private val listViewFragment = ListViewFragment()
    private val searchFragment = SearchFragment()
    private val loanFragment = LoanFragment()
    private val mapFragment = MapsFragment()
    private val detailsFragment = PropertyDetailsFragment()

    val allProperties: LiveData<List<Property>> = repository.allProperties


    val allPhoto: LiveData<List<PropertyPhoto>> = photoRepository.allPhotos

    val filteredProperties = MutableLiveData<List<Property>>()

    fun deleteProperty(property: Property) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(property)
    }

    fun updateProperty(property: Property) = viewModelScope.launch(Dispatchers.IO){
        repository.update(property)
    }

    fun addProperty(property: Property) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(property)
    }

    fun deletePhoto(photo: PropertyPhoto) = viewModelScope.launch(Dispatchers.IO){
        photoRepository.delete(photo)
    }

    fun filterProperty(
        priceMini: Int, priceMax: Int, surfaceMini: Int, surfaceMax: Int, roomMini: Int,
        roomMax: Int)
    : LiveData<List<Property>> {
        return repository.select(priceMini, priceMax, surfaceMini, surfaceMax, roomMini, roomMax)

    }


}