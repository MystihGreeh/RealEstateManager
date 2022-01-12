package com.example.realestatemanager.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.realestatemanager.R
import com.example.realestatemanager.databinding.FragmentAddPropertyBinding

class AddPropertyFragment: Fragment() {



    val managedBy:Array<String> = arrayOf("Charlotte", "Bastien", "David", "Christelle")
    val numberOfRooms = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10+")
    val typeOfGood:Array<String> = arrayOf("Flat", "Townhouse", "Penthouse", "House", "Duplex")


    private var bindingNewPropertyFragment: FragmentAddPropertyBinding? = null
    private val binding get() = bindingNewPropertyFragment!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindingNewPropertyFragment = FragmentAddPropertyBinding.inflate(inflater, container, false)

        // Settting the AutoComplete TextView
        val typeOfGoodArrayAdapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, typeOfGood)
        val managedByArrayAdapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, managedBy)
        val roomsArrayAdapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, numberOfRooms)

        binding.autoCompleteType.setAdapter(typeOfGoodArrayAdapter)
        binding.autoCompleteSeller.setAdapter(managedByArrayAdapter)
        binding.autoCompleteRooms.setAdapter(roomsArrayAdapter)
        binding.autoCompleteBedrooms.setAdapter(roomsArrayAdapter)




        return binding.root
    }

    private fun saveProperty(){
        binding.busCheck.isChecked = false
        binding.schoolCheck.isChecked = false
        binding.parkingCheck.isChecked = false
        binding.parksCheck.isChecked = false
        binding.marketCheck.isChecked = false
        binding.allCheck.isChecked = false

        binding.busCheck.setOnCheckedChangeListener { buttonView, isChecked ->  }
        binding.schoolCheck.setOnCheckedChangeListener { buttonView, isChecked ->  }
        binding.parkingCheck.setOnCheckedChangeListener { buttonView, isChecked ->  }
        binding.parksCheck.setOnCheckedChangeListener { buttonView, isChecked ->  }
        binding.marketCheck.setOnCheckedChangeListener { buttonView, isChecked ->  }
        binding.allCheck.setOnCheckedChangeListener { buttonView, isChecked ->  }

    }





}