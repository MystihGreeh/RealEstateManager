package com.example.realestatemanager.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.realestatemanager.R
import com.example.realestatemanager.databinding.FragmentPropertyDetailsBinding
import com.example.realestatemanager.viewModel.MainActivityViewModel


class PropertyDetailsFragment() : Fragment() {

    private var bindingDetailsFragment: FragmentPropertyDetailsBinding? = null
    private val binding get() = bindingDetailsFragment!!
    var photoList = mutableListOf<String>()

    val viewModel : MainActivityViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingDetailsFragment = FragmentPropertyDetailsBinding.inflate(inflater, container, false)

        return binding.root}

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val photoID = arguments?.get("photoId")
        //val photoPropertyid = arguments?.get("photoPropertyId")
        //val photoString = arguments?.get("photoString")
        val propertyID = arguments?.get("propertyId")
        val propertyType = arguments?.get("propertyType")
        val propertyPrice = arguments?.get("propertyPrice")
        val propertyDescription = arguments?.get("propertyDescription")
        val propertyRooms = arguments?.get("propertyRooms")
        val propertySurface = arguments?.get("propertySurface")
        val propertyBedrooms = arguments?.get("propertyBedrooms")
        val propertySeller = arguments?.get("propertySeller")
        val propertyStreet = arguments?.get("propertyAddress")
        val propertyCity = arguments?.get("propertyCity")
        val propertyPostalCode = arguments?.get("propertyPostalCode")
        val propertyCountry = arguments?.get("propertyCountry")
        val propertyNearbySchool = arguments?.getBoolean("propertyNearbySchool", false)
        val propertyNearbyTransportation =
            arguments?.getBoolean("propertyNearbyTransportation", false)
        val propertyNearbyParks = arguments?.getBoolean("propertyNearbyParks", false)
        val propertyNearbyParking = arguments?.getBoolean("propertyNearbyParking", false)
        val propertyNearbyMarket = arguments?.getBoolean("propertyNearbyMarket", false)
        val propertyNearbyAll = arguments?.getBoolean("propertyNearbyAll", false)
        val propertyOnSale = arguments?.getBoolean("propertyOnSale", false)
        val propertyCreationTimeStamp = arguments?.get("propertyCreatedTimeStamp")
        val propertySoldTimeStamp = arguments?.get("propertySoldTimeStamp")
        //val propertyLatitude = arguments?.get("propertyLatitude")
        //val propertyLongitude = arguments?.get("propertyLongitude")
        val propertyStaticMapUrl = arguments?.get("propertyStaticMapUrl")
        binding.propertyTypeTextview.text = propertyType.toString()
        binding.propertyPriceTextview.text = propertyPrice.toString()
        binding.descriptionText.text = propertyDescription.toString()
        binding.propertyNbrRoomsTextview.text = propertyRooms.toString()
        binding.propertyNbrBedroomsTextview.text = propertyBedrooms.toString()
        binding.propertySurfaceTextview.text = propertySurface.toString()
        binding.seller.text = propertySeller.toString()
        binding.street.text = propertyStreet.toString()
        binding.city.text = propertyCity.toString()
        binding.country.text = propertyCountry.toString()
        binding.postalCode.text = propertyPostalCode.toString()
        if (propertyNearbySchool == true) {
            binding.schoolNearbyTextview.setImageResource(R.drawable.ic_baseline_check_24)
        } else binding.schoolNearbyTextview.setImageResource(R.drawable.ic_baseline_close_24)
        if (propertyNearbyParks == true) {
            binding.parkNearbyTextview.setImageResource(R.drawable.ic_baseline_check_24)
        } else binding.parkNearbyTextview.setImageResource(R.drawable.ic_baseline_close_24)
        if (propertyNearbyParking == true) {
            binding.parkingNearbyTextview.setImageResource(R.drawable.ic_baseline_check_24)
        } else binding.parkingNearbyTextview.setImageResource(R.drawable.ic_baseline_close_24)
        if (propertyNearbyMarket == true) {
            binding.marketNearbyTextview.setImageResource(R.drawable.ic_baseline_check_24)
        } else binding.marketNearbyTextview.setImageResource(R.drawable.ic_baseline_close_24)
        if (propertyNearbyTransportation == true) {
            binding.transportationNearbyTextview.setImageResource(R.drawable.ic_baseline_check_24)
        } else binding.transportationNearbyTextview.setImageResource(R.drawable.ic_baseline_close_24)
        if (propertyNearbyAll == true) {
            binding.allNearbyTextview.setImageResource(R.drawable.ic_baseline_check_24)
        } else binding.allNearbyTextview.setImageResource(R.drawable.ic_baseline_close_24)
        binding.creationDate.setText(R.string.creation)
        binding.creationDate.setTextColor(Color.parseColor("#04711C"))
        binding.creationTimestamp.setText(" $propertyCreationTimeStamp ")
        if (propertyOnSale == false) {
            binding.isSold.setText(R.string.on_sale)
            binding.isSold.setTextColor(Color.parseColor("#04711C"))
        } else {
            binding.isSold.setText(R.string.sold_the)
            binding.isSold.setTextColor(Color.parseColor("#C50017"))
            binding.soldTimestamp.setText("$propertySoldTimeStamp")
        }

        Glide.with(this)
            .load(propertyStaticMapUrl)
            .into(binding.staticMap)

        binding.backButton.setOnClickListener {
            val listeView = ListViewFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.activity_main_framelayout, listeView)?.commit()
        }

        val recyclerView = binding.horizontalRecyclerView
        //recyclerView?.adapter = PhotoAdapter(photoList, context)
    }

}