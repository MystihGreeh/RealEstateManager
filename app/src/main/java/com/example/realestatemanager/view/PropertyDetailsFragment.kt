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


        val property: Property= arguments?.getSerializable("property") as Property
        println(property.typeOfGood)

        val propertyStaticMapUrl = arguments?.get("propertyStaticMapUrl")
        binding.propertyTypeTextview.text = property.typeOfGood.toString()
        binding.propertyPriceTextview.text = property.priceInDollars.toString()
        binding.descriptionText.text = property.description.toString()
        binding.propertyNbrRoomsTextview.text = property.numberOfRoom.toString()
        binding.propertyNbrBedroomsTextview.text = property.numberOfBedroom.toString()
        binding.propertySurfaceTextview.text = property.surfaceInMeters.toString()
        binding.seller.text = property.agent.toString()
        binding.street.text = property.street.toString()
        binding.city.text = property.city.toString()
        binding.country.text = property.country.toString()
        binding.postalCode.text = property.postalcode.toString()
        if (property.school == true) {
            binding.schoolNearbyTextview.setImageResource(R.drawable.ic_baseline_check_24)
        } else binding.schoolNearbyTextview.setImageResource(R.drawable.ic_baseline_close_24)
        if (property.parks == true) {
            binding.parkNearbyTextview.setImageResource(R.drawable.ic_baseline_check_24)
        } else binding.parkNearbyTextview.setImageResource(R.drawable.ic_baseline_close_24)
        if (property.parking == true) {
            binding.parkingNearbyTextview.setImageResource(R.drawable.ic_baseline_check_24)
        } else binding.parkingNearbyTextview.setImageResource(R.drawable.ic_baseline_close_24)
        if (property.market == true) {
            binding.marketNearbyTextview.setImageResource(R.drawable.ic_baseline_check_24)
        } else binding.marketNearbyTextview.setImageResource(R.drawable.ic_baseline_close_24)
        if (property.transportation == true) {
            binding.transportationNearbyTextview.setImageResource(R.drawable.ic_baseline_check_24)
        } else binding.transportationNearbyTextview.setImageResource(R.drawable.ic_baseline_close_24)
        if (property.selectAll == true) {
            binding.allNearbyTextview.setImageResource(R.drawable.ic_baseline_check_24)
        } else binding.allNearbyTextview.setImageResource(R.drawable.ic_baseline_close_24)
        binding.creationDate.setText(R.string.creation)
        binding.creationDate.setTextColor(Color.parseColor("#04711C"))
        binding.creationTimestamp.setText(" ${property.createdTimestamp} ")
        if (property.isSold == false) {
            binding.isSold.setText(R.string.on_sale)
            binding.isSold.setTextColor(Color.parseColor("#04711C"))
        } else {
            binding.isSold.setText(R.string.sold_the)
            binding.isSold.setTextColor(Color.parseColor("#C50017"))
            binding.soldTimestamp.setText("${property.soldTimestamp}")
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
                fullAddress = "${property.street}, ${property.postalcode}, ${property.city}, ${property.country}"
                fullAddressList = geocoder.getFromLocationName(fullAddress, 1)
                latitude = fullAddressList[0].latitude
                longitude = fullAddressList[0].longitude
                propertyStaticMapUrlWithInternet = "http://maps.google.com/maps/api/staticmap?center=" + latitude + "," + longitude + "&zoom=15&size=200x200&sensor=false&key=" + GOOGLE_KEY
                Glide.with(this)
                    .load(propertyStaticMapUrlWithInternet)
                    .into(binding.staticMap)
                val updateProperty = Property(
                    property.id, property.typeOfGood, property.priceInDollars.toString().toInt(),
                    property.surfaceInMeters.toString().toInt(), property.numberOfRoom.toString().toInt(), property.numberOfBedroom.toString().toInt(),
                    property.description, property.street, property.postalcode,
                    property.city, property.country, property.transportation, property.market,
                    property.parks, property.parking, property.school, property.selectAll, property.isSold, property.agent,
                    property.propertyImage, property.createdTimestamp, property.soldTimestamp, fullAddress,
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
            //bundle.putString("modify", "Edit")
            bundle.putSerializable("property", property)
            val addPropertyActivity = AddPropertyActivity()

            //addPropertyActivity.intent.getSerializableExtra("property")

            val intent = Intent(requireContext(), AddPropertyActivity::class.java)
            intent.putExtra("property", property)
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




    companion object {
        const val DATA_KEY = "data_key"
    }

}
