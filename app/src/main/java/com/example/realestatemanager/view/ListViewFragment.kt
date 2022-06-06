package com.example.realestatemanager.view

import android.content.Intent
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.realestatemanager.R
import com.example.realestatemanager.databinding.FragmentListBinding
import com.example.realestatemanager.model.Property
import com.example.realestatemanager.viewModel.MainActivityViewModel

class ListViewFragment : Fragment(), PropertyClickInterface, PropertyDeleteInterface{

    private var bindingListFragment: FragmentListBinding? = null
    private val binding get() = bindingListFragment!!

    val viewModel : MainActivityViewModel by activityViewModels()


    companion object {
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingListFragment = FragmentListBinding.inflate(inflater, container, false)

        viewModel.allProperties.observe(this, {
            list -> binding.propertyListRecyclerView.adapter = PropertyAdapter(this, list, this, this)
        })

        addButtonClicked()

        return binding.root}



    private fun addButtonClicked(){
        binding.floatingActionButton.setOnClickListener{
            val intent = Intent(this@ListViewFragment.requireContext(), AddPropertyActivity::class.java)
            intent.setFlags(FLAG_GRANT_READ_URI_PERMISSION)
            startActivity(intent)
            }
    }
    override fun onDeleteClick(property: Property) {
        viewModel.deleteProperty(property)
        Toast.makeText(requireContext(), "${property.city} Deleted", Toast.LENGTH_LONG).show()
    }

    override fun onPropertyClick(property: Property) {

        Toast.makeText(requireContext(), "${property.typeOfGood} ${property.city}", Toast.LENGTH_LONG).show()

        val bundle = Bundle()

        bundle.putLong("id", property.id!!)
        bundle.putString("propertyType", property.typeOfGood)
        bundle.putString("propertyPrice", property.priceInDollars)
        bundle.putString("propertySurface", property.surfaceInMeters)
        bundle.putString("propertyRooms", property.numberOfRoom)
        bundle.putString("propertyBedrooms", property.numberOfBedroom)
        bundle.putBoolean("propertyNearbySchool", property.school)
        bundle.putBoolean("propertyNearbyTransportation", property.transportation)
        bundle.putBoolean("propertyNearbyMarket", property.market)
        bundle.putBoolean("propertyNearbyParks", property.parks)
        bundle.putBoolean("propertyNearbyParking", property.parking)
        bundle.putBoolean("propertyAll", property.selectAll)
        bundle.putString("propertyDescription", property.description)
        bundle.putString("propertyAddress", property.street)
        bundle.putString("propertyPostalCode", property.postalcode)
        bundle.putString("propertyCity", property.city)
        bundle.putString("propertyCountry", property.country)
        bundle.putString("propertySeller", property.agent)
        bundle.putBoolean("propertyOnSale", property.isSold)
        bundle.putString("propertyCreatedTimeStamp", property.createdTimestamp)
        bundle.putString("propertySoldTimeStamp", property.soldTimestamp)
        //bundle.putDouble("propertyLatitude", property.latitude)
        //bundle.putSerializable("propertyLongitude", property.longitude)
        bundle.putString("propertyStaticMapUrl", property.propertyStaticMapUrl)
        bundle.putString("propertyImage", property.propertyImage)

        val detailsFragment = PropertyDetailsFragment()
        detailsFragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.details_main_framelayout, detailsFragment)?.commit()


    }


}