package com.example.realestatemanager.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.realestatemanager.BuildConfig
import com.example.realestatemanager.R
import com.example.realestatemanager.adapter.PhotoAdapter
import com.example.realestatemanager.databinding.FragmentPropertyDetailsBinding
import com.example.realestatemanager.model.Property
import com.example.realestatemanager.model.PropertyPhoto
import com.example.realestatemanager.utils.Utils
import com.example.realestatemanager.viewModel.MainActivityViewModel
import java.util.*


class PropertyDetailsFragment() : Fragment() {

    private var bindingDetailsFragment: FragmentPropertyDetailsBinding? = null
    private val binding get() = bindingDetailsFragment!!
    var photoList = mutableListOf<PropertyPhoto>()
    var descriptionList = mutableListOf<String>()
    lateinit var propertyStaticMapUrlWithInternet: String
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<PhotoAdapter.ViewHolder>? = null

    var latitude: Double = 0.0
    var longitude: Double = 0.0
    var fullAddress: String = ""
    lateinit var fullAddressList: List<Address>
    val GOOGLE_KEY: String = BuildConfig.GOOGLE_KEY

    val viewModel : MainActivityViewModel by activityViewModels()

    val propertyId = arguments?.get("id")

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




        val propertyId = arguments?.get("id")
        val property = arguments?.getSerializable("property")
        val propertyModify = arguments?.get("modify")
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
        val propertyLatitude = arguments?.get("propertyLatitude")
        val propertyLongitude = arguments?.get("propertyLongitude")
        val propertyAgent = arguments?.get("agent")
        val propertyImage = arguments?.get("image")
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

        if (propertyStaticMapUrl == "") {
            if (!Utils.Companion.isInternetAvailable(requireContext()) || !Utils.Companion.isWifiAvailable(requireContext())){
                Glide.with(this)
                    .load(propertyStaticMapUrl)
                    .into(binding.staticMap)
                println("internet not available")
            } else {
                println("internet available")
                val geocoder = Geocoder(requireContext(), Locale.getDefault()) // initializing Geocoder
                fullAddress = "$propertyStreet, $propertyPostalCode, $propertyCity, $propertyCountry"
                fullAddressList = geocoder.getFromLocationName(fullAddress, 1)
                latitude = fullAddressList[0].latitude
                longitude = fullAddressList[0].longitude
                propertyStaticMapUrlWithInternet = "http://maps.google.com/maps/api/staticmap?center=" + latitude + "," + longitude + "&zoom=15&size=200x200&sensor=false&key=" + GOOGLE_KEY
                Glide.with(this)
                    .load(propertyStaticMapUrlWithInternet)
                    .into(binding.staticMap)
                val updateProperty = Property(
                    propertyId as Long?, propertyType.toString(), propertyPrice.toString(),
                    propertySurface.toString(), propertyRooms.toString(), propertyBedrooms.toString(),
                    propertyDescription.toString(), propertyStreet.toString(), propertyPostalCode.toString(),
                    propertyCity.toString(), propertyCountry.toString(), propertyNearbyTransportation!!, propertyNearbyMarket!!,
                    propertyNearbyParks!!, propertyNearbyParking!!, propertyNearbySchool!!, propertyNearbyAll!!, propertyOnSale!!, propertyAgent.toString(),
                    propertyImage.toString(), propertyCreationTimeStamp.toString(), propertySoldTimeStamp.toString(), fullAddress,
                    longitude, latitude, propertyStaticMapUrlWithInternet)
                viewModel.updateProperty(updateProperty)
            }
        } else {
            Glide.with(this)
                .load(propertyStaticMapUrl)
                .into(binding.staticMap)
            println("internet not available")
        }
        back()
        getPropertyPhotos()

        binding.modifyButton.setOnClickListener {
            val bundle = Bundle()
            if (propertyModify != null){
                //bundle.putSerializable("property")
                bundle.putSerializable("propertyType", propertyType as String?)
                bundle.putSerializable("propertyPrice", propertyPrice as String)
                bundle.putSerializable("propertySurface", propertySurface as String)
            }
            bundle.putString("modify", "Edit")


            val intent = Intent(requireContext(), AddPropertyActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getPropertyPhotos(){
        viewModel.allPhoto.observe(this, {
                photos ->
            if (photos != null){
                photos.forEach{
                        propertyPhoto -> val propertyIdPhoto = propertyPhoto.property
                    val propertyID = arguments?.get("id")
                    if (propertyIdPhoto == propertyID){
                        photoList.add(propertyPhoto)
                    }
                }
                initiateRecyclerView()
            }
        })
    }
    private fun initiateRecyclerView() {
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.horizontalRecyclerView.layoutManager = layoutManager
        adapter = PhotoAdapter(photoList)
        binding.horizontalRecyclerView.adapter = adapter

    }


    fun back(){
        binding.backButton.setOnClickListener {
            val listeView = ListViewFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.activity_main_framelayout, listeView)?.commit()
        }
    }

    fun modifyProperty(){

    }


    companion object {
        const val DATA_KEY = "data_key"
    }

}
