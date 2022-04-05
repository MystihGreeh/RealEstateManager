package com.example.realestatemanager.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.realestatemanager.databinding.FragmentListBinding
import com.example.realestatemanager.model.Property
import com.example.realestatemanager.viewModel.MainActivityViewModel

class ListViewFragment : Fragment(), PropertyClickInterface{

    private var bindingListFragment: FragmentListBinding? = null
    private val binding get() = bindingListFragment!!

    val viewModel : MainActivityViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindingListFragment = FragmentListBinding.inflate(inflater, container, false)

       viewModel.allProperties.observe(this, {
            list -> binding?.propertyListRecyclerView?.adapter = PropertyAdapter(list, this)

        })



        addButtonClicked()

        return binding.root}



    private fun addButtonClicked(){
        binding.floatingActionButton.setOnClickListener{
            val intent = Intent(this@ListViewFragment.requireContext(), AddPropertyActivity::class.java)
            startActivity(intent)
            }
    }

    override fun onPropertyClick(property: Property) {
        Toast.makeText(requireContext(), "${property.city} clicked", Toast.LENGTH_LONG).show()
        val intent = Intent(this@ListViewFragment.requireContext(), PropertyDetailsActivity::class.java)
        intent.putExtra("propertyID", property.id)
        intent.putExtra("propertyType", property.typeOfGood)
        intent.putExtra("propertyPrice", property.priceInDollars)
        intent.putExtra("propertySurface", property.surfaceInMeters)
        intent.putExtra("propertyRooms", property.numberOfRoom)
        intent.putExtra("propertyBedrooms", property.numberOfBedroom)
        intent.putExtra("propertySchool", property.school)
        intent.putExtra("propertyTransportation", property.transportation)
        intent.putExtra("propertyMarket", property.market)
        intent.putExtra("propertyParks", property.parks)
        intent.putExtra("propertyParking", property.parking)
        intent.putExtra("propertyAll", property.selectAll)
        intent.putExtra("propertyDescription", property.description)
        intent.putExtra("propertyAddress", property.street)
        intent.putExtra("propertyPostalCode", property.postalcode)
        intent.putExtra("propertyCity", property.city)
        intent.putExtra("propertyCountry", property.country)
        intent.putExtra("propertySeller", property.agent)
        startActivity(intent)

    }


}