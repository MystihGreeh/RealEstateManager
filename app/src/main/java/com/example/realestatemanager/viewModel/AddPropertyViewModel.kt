package com.example.realestatemanager.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realestatemanager.model.Property
import com.example.realestatemanager.model.PropertyPhoto
import com.example.realestatemanager.repositories.PhotoPropertyRepository
import com.example.realestatemanager.repositories.PropertyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddPropertyViewModel(private val repository: PropertyRepository, private val photoRepository : PhotoPropertyRepository) : ViewModel() {

    val allProperties: LiveData<List<Property>> = repository.allProperties

    val allPhotos: LiveData<List<PropertyPhoto>> = photoRepository.allPhotos

    fun deleteProperty(property: Property) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(property)
    }

    fun updateProperty(property: Property) = viewModelScope.launch(Dispatchers.IO){
        repository.update(property)
    }

    fun addProperty(property: Property) : LiveData<Long> {
        val liveData = MutableLiveData<Long>()
        viewModelScope.launch(Dispatchers.IO) {
            val id = repository.insert(property)
            liveData.postValue(id)
        }
        return liveData
    }

    fun addPhoto(photo: PropertyPhoto) = viewModelScope.launch(Dispatchers.IO){
        photoRepository.insert(photo)
    }


}


