package com.example.realestatemanager.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.realestatemanager.R
import com.example.realestatemanager.api.RealEstateManagerApplication
import com.example.realestatemanager.databinding.ActivityPropertyDetailsBinding
import com.example.realestatemanager.viewModel.PropertyDetailsViewModelActivity
import com.example.realestatemanager.viewModel.ViewModelFactory

class PropertyDetailsActivity : AppCompatActivity(){

    private var binding: ActivityPropertyDetailsBinding? = null

    private val viewModel: PropertyDetailsViewModelActivity by viewModels {
        ViewModelFactory((application as RealEstateManagerApplication).repository)
    }


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_details)
        binding = ActivityPropertyDetailsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val propertyID = intent.getStringExtra("propertyId")
        val propertyType = intent.getStringExtra("propertyType")
        val propertyPrice = intent.getStringExtra("propertyPrice")
        val propertyDescription = intent.getStringExtra("propertyDescription")
        val propertyRooms = intent.getStringExtra("propertyRooms")
        val propertySurface = intent.getStringExtra("propertySurface")
        val propertyBedrooms = intent.getStringExtra("propertyBedrooms")
        val propertySeller = intent.getStringExtra("propertySeller")
        val propertyStreet = intent.getStringExtra("propertyAddress")
        val propertyCity = intent.getStringExtra("propertyCity")
        val propertyPostalCode = intent.getStringExtra("propertyPostalCode")
        val propertyCountry = intent.getStringExtra("propertyCountry")
        val propertyNearbySchool = intent.getBooleanExtra("propertyNearbySchool", false)
        val propertyNearbyTransportation = intent.getBooleanExtra("propertyNearbyTransportation", false)
        val propertyNearbyParks = intent.getBooleanExtra("propertyNearbyParks", false)
        val propertyNearbyParking = intent.getBooleanExtra("propertyNearbyparking", false)
        val propertyNearbyMarket = intent.getBooleanExtra("propertyNearbyMarket", false)
        val propertyNearbyAll = intent.getBooleanExtra("propertyNearbyAll", false)
        val propertyOnSale = intent.getBooleanExtra("propertyOnSale", false)
        val propertyTimeStamp = intent.getStringExtra("propertyTimeStamp")

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
        if (propertyNearbySchool == true) {
            binding?.schoolNearbyTextview?.setImageResource(R.drawable.ic_baseline_check_24)
        } else binding?.schoolNearbyTextview?.setImageResource(R.drawable.ic_baseline_close_24)
        if (propertyNearbyParks == true) {
            binding?.parkNearbyTextview?.setImageResource(R.drawable.ic_baseline_check_24)
        } else binding?.parkNearbyTextview?.setImageResource(R.drawable.ic_baseline_close_24)
        if (propertyNearbyParking == true) {
            binding?.parkingNearbyTextview?.setImageResource(R.drawable.ic_baseline_check_24)
        } else binding?.parkingNearbyTextview?.setImageResource(R.drawable.ic_baseline_close_24)
        if (propertyNearbyMarket == true) {
            binding?.marketNearbyTextview?.setImageResource(R.drawable.ic_baseline_check_24)
        } else binding?.marketNearbyTextview?.setImageResource(R.drawable.ic_baseline_close_24)
        if (propertyNearbyTransportation == true) {
            binding?.transportationNearbyTextview?.setImageResource(R.drawable.ic_baseline_check_24)
        } else binding?.transportationNearbyTextview?.setImageResource(R.drawable.ic_baseline_close_24)
        if (propertyNearbyAll == true) {
            binding?.allNearbyTextview?.setImageResource(R.drawable.ic_baseline_check_24)
        } else binding?.allNearbyTextview?.setImageResource(R.drawable.ic_baseline_close_24)
        if (propertyOnSale == false) {
            binding?.isSold?.setText(R.string.on_sale)
            binding?.isSold?.setTextColor(Color.parseColor("#04711C"))
        } else {
            binding?.isSold?.setText(R.string.sold)
            binding?.isSold?.setTextColor(Color.parseColor("#C50017"))
            binding?.soldTimestamp?.setText(" le $propertyTimeStamp ")
            binding?.soldTimestamp?.setTextColor(Color.parseColor("#C50017"))
        }
        //var geocoder = Geocoder(this, Locale.getDefault())
        //var addresses: MutableList<Address>? = geocoder.getFromLocationName(binding?.city.toString(), 2)
        //var address: Address? = addresses?.get(0)

        binding?.modifyButton?.setOnClickListener {
            Toast.makeText(this, "clicked", Toast.LENGTH_LONG).show()
            val intent = Intent(this@PropertyDetailsActivity, AddPropertyActivity::class.java)
            intent.putExtra("propertyId", propertyID)
            intent.putExtra("propertyType", propertyType)
            intent.putExtra("propertyPrice", propertyPrice)
            intent.putExtra("propertySurface", propertySurface)
            intent.putExtra("propertyRooms", propertyRooms)
            intent.putExtra("propertyBedrooms", propertyBedrooms)
            intent.putExtra("propertyNearbySchool", propertyNearbySchool)
            intent.putExtra("propertyNearbyTransportation", propertyNearbyTransportation)
            intent.putExtra("propertyNearbyMarket", propertyNearbyMarket)
            intent.putExtra("propertyNearbyParks", propertyNearbyParks)
            intent.putExtra("propertyNearbyParking", propertyNearbyParking)
            intent.putExtra("propertyAll", propertyNearbyAll)
            intent.putExtra("propertyDescription", propertyDescription)
            intent.putExtra("propertyAddress", propertyStreet)
            intent.putExtra("propertyPostalCode", propertyPostalCode)
            intent.putExtra("propertyCity", propertyCity)
            intent.putExtra("propertyCountry", propertyCountry)
            intent.putExtra("propertySeller", propertySeller)
            intent.putExtra("propertyOnSale", propertyOnSale)
            intent.putExtra("propertyTimeStamp", propertyTimeStamp)
            //intent.putExtra("propertyImage", prop)
            startActivity(intent)
        }
        binding?.backButton?.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }


}

