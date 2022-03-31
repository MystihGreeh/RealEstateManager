package com.example.realestatemanager.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.realestatemanager.repositories.PropertyRepository

class ViewModelFactory (private val repository: PropertyRepository) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AddPropertyViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AddPropertyViewModel(repository) as T
            }
            if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainActivityViewModel(repository) as T
            }
            if (modelClass.isAssignableFrom(FragmentListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FragmentListViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

}
