package com.example.realestatemanager.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.realestatemanager.R
import com.example.realestatemanager.databinding.FragmentSearchBinding
import com.example.realestatemanager.utils.Utils
import com.example.realestatemanager.viewModel.MainActivityViewModel

class SearchFragment : Fragment() {
    companion object {

    }

    private var bindingSearchFragment: FragmentSearchBinding? = null
    private val binding get() = bindingSearchFragment!!

    var propertySchool:Boolean = false
    var propertyParks :Boolean = false
    var propertyParking:Boolean = false
    var propertyMarket :Boolean = false
    var propertyAllNearby:Boolean = false
    var propertyOnSale:Boolean = false
    var propertyDateOfSale : String = ""
    var propertyBus :Boolean = false
    var houseType :Boolean = false
    var duplexType :Boolean = false
    var flatType :Boolean = false
    var charlotte :Boolean = false
    var sold :Boolean = false
    var withPicture :Boolean = false
    var david :Boolean = false
    var sylvain :Boolean = false
    var christelle :Boolean = false
    var penthouseType :Boolean = false
    var propertyAllType:Boolean = false

    lateinit var propertySeller:String
    lateinit var propertyType:String

    val viewModel : MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingSearchFragment = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onCheckboxClicked(view)
        onButtonClicked()
        searchProperties()
        this.onButtonClicked()
    }

    //--------------------------------------------------------------------------------------------//
    //---------------------------- SETTING SPINER AND CHECKBOX -----------------------------------//
    //--------------------------------------------------------------------------------------------//

    // Setting checkbox

    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked
            //When user pick a nearby place(s)
            when (view.id) {
                R.id.house_check -> {houseType = checked}
                R.id.penthouse_check -> {penthouseType = checked}
                R.id.duplex_check -> {duplexType = checked}
                R.id.flat_check -> {flatType = checked}
                R.id.type_all_check -> {
                    propertyAllType = checked
                    houseType = checked
                    penthouseType = checked
                    duplexType = checked
                    flatType = checked}
                R.id.is_sold_check -> {propertyOnSale = checked
                    propertyDateOfSale = Utils.Companion.getTodayDate()!!}
                R.id.charlotte_check -> {charlotte = checked}
                R.id.david_check -> {david = checked}
                R.id.sylvain_check -> {sylvain = checked}
                R.id.christelle_check -> {christelle = checked}
                R.id.sold -> {sold = checked}
                R.id.with_picture -> {withPicture = checked}
                R.id.bus_check -> {propertyBus = checked}
                R.id.market_check -> {propertyMarket = checked}
                R.id.parking_check -> {propertyParking = checked}
                R.id.parks_check -> {propertyParks = checked}
                R.id.school_check -> {propertySchool = checked}
                R.id.nearby_all_check -> {
                    propertyAllNearby = checked
                    propertyBus = checked
                    propertyMarket = checked
                    propertyParking = checked
                    propertyParks = checked
                    propertySchool = checked}
            }
        }
    }

    fun searchProperties(){
    val propertyPriceMini = binding.priceMini.text.toString()
    val propertyPriceMax = binding.priceMax.text.toString()
    val propertyRoomMini = binding.roomMini.text.toString()
    val propertyRoomMax = binding.roomMax.text.toString()
    val propertyBedroomMini = binding.bedroomMini.text.toString()
    val propertyBedroomMax = binding.bedroomMax.text.toString()
    val propertySurfaceMini = binding.surfaceMini.text.toString()
    val propertySurfaceMax = binding.surfaceMax.text.toString()

        //Searching property using ViewModel

    }

    private fun onButtonClicked(){
        binding.searchProperty.setOnClickListener{
            searchProperties()
            //viewModel.filterProperty()
            val intent = Intent(this@SearchFragment.requireContext(), ListViewFragment::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.fragmentSearch
    }

    override fun onPause() {
        super.onPause()
        binding.fragmentSearch
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.fragmentSearch
    }
}