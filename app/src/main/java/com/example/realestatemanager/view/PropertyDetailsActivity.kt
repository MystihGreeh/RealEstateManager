package com.example.realestatemanager.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.realestatemanager.R
import com.example.realestatemanager.api.RealEstateManagerApplication
import com.example.realestatemanager.databinding.ActivityPropertyDetailsBinding
import com.example.realestatemanager.viewModel.PropertyDetailsViewModelActivity
import com.example.realestatemanager.viewModel.ViewModelFactory

class PropertyDetailsActivity : AppCompatActivity() {

    private var binding: ActivityPropertyDetailsBinding? = null

    private val viewModel: PropertyDetailsViewModelActivity by viewModels {
        ViewModelFactory((application as RealEstateManagerApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_details)
        binding = ActivityPropertyDetailsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val propertyType = intent.getStringExtra("propertyType")
        val propertyPrice = intent.getStringExtra("propertyPrice")
        val propertyDescription = intent.getStringExtra("propertyDescription")
        val propertyRooms = intent.getStringExtra("propertyRooms")
        val propertySurface = intent.getStringExtra("propertySurface")
        val propertyBedrooms = intent.getStringExtra("propertyBedrooms")
        val propertySeller = intent.getStringExtra("propertySeller")
        val propertyStreet = intent.getStringExtra("propertyStreet")
        val propertyCity = intent.getStringExtra("propertyCity")
        val propertyPostalCode = intent.getStringExtra("propertyPostalCode")
        val propertyCountry = intent.getStringExtra("propertyCountry")

        binding?.propertyTypeTextview?.setText((propertyType))
        binding?.propertyPriceTextview?.setText((propertyPrice))
        binding?.descriptionText?.setText((propertyDescription))
        binding?.propertyNbrRoomsTextview?.setText((propertyRooms))
        binding?.propertyNbrBedroomsTextview?.setText((propertyBedrooms))
        binding?.propertySurfaceTextview?.setText((propertySurface))
        binding?.seller?.setText((propertySeller))
        binding?.street?.setText((propertyStreet))
        binding?.city?.setText((propertyCity))
        binding?.country?.setText((propertyCountry))
        binding?.postalCode?.setText((propertyPostalCode))

    }


}