package com.example.realestatemanager.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.realestatemanager.repositories.PhotoPropertyRepository
import com.example.realestatemanager.repositories.PropertyRepository

class ViewModelFactory (private val repository: PropertyRepository, private val photoRepository: PhotoPropertyRepository) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AddPropertyViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AddPropertyViewModel(repository, photoRepository) as T
            }
            if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainActivityViewModel(repository, photoRepository) as T
            }
            if (modelClass.isAssignableFrom(PropertyDetailsViewModelActivity::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PropertyDetailsViewModelActivity(repository, photoRepository) as T
            }
            if (modelClass.isAssignableFrom(FragmentListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FragmentListViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

}
