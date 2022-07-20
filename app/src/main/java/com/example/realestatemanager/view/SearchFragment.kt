package com.example.realestatemanager.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.realestatemanager.R
import com.example.realestatemanager.databinding.FragmentSearchBinding
import com.example.realestatemanager.viewModel.MainActivityViewModel

class SearchFragment : Fragment(){
    companion object {

    }

    private var bindingSearchFragment: FragmentSearchBinding? = null
    private val binding get() = bindingSearchFragment!!

    lateinit var propertySurfaceMax:String
    lateinit var propertyPriceMini:String
    lateinit var propertyPriceMax:String
    lateinit var propertyRoomMini:String
    lateinit var propertyRoomMax:String
    lateinit var propertyBedroomMini:String
    lateinit var propertyBedroomMax:String
    lateinit var propertySurfaceMini:String
    var typeList =ArrayList<String>()



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

        onButtonClicked()
        this.onButtonClicked()
        typeList.add("Maison")
        typeList.add("Duplex")
        typeList.joinToString(prefix = "'", postfix = "'", separator =",")
    }

    //--------------------------------------------------------------------------------------------//
    //---------------------------- SETTING SPINNER AND CHECKBOX -----------------------------------//
    //--------------------------------------------------------------------------------------------//

    fun searchProperties() {

        propertyPriceMini = binding.priceMini.text.toString()
        propertyPriceMax = binding.priceMax.text.toString()
        propertyRoomMini = binding.roomMini.text.toString()
        propertyRoomMax = binding.roomMax.text.toString()
        propertyBedroomMini = binding.bedroomMini.text.toString()
        propertyBedroomMax = binding.bedroomMax.text.toString()
        propertySurfaceMini = binding.surfaceMini.text.toString()
        propertySurfaceMax = binding.surfaceMax.text.toString()



    }

    fun beginTheSearch(){

        if (propertyPriceMini == ""){ propertyPriceMini = "0" }
        if (propertyPriceMax == ""){ propertyPriceMax = "999999999" }
        if (propertySurfaceMini == ""){ propertySurfaceMini = "0" }
        if (propertySurfaceMax == ""){ propertySurfaceMax = "9999" }
        if (propertyRoomMini == ""){ propertyRoomMini = "0" }
        if (propertyRoomMax == ""){ propertyRoomMax = "999" }
        if (propertyBedroomMini == ""){ propertyBedroomMini = "0" }
        if (propertyBedroomMax == ""){ propertyBedroomMax = "999" }
    }
    private fun onButtonClicked(){
        binding.searchProperty.setOnClickListener{


            searchProperties()
            beginTheSearch()
            viewModel.filterProperty(propertyPriceMini.toInt(), propertyPriceMax.toInt(), propertySurfaceMini.toInt(),
                propertySurfaceMax.toInt(), propertyRoomMini.toInt(), propertyRoomMax.toInt(),

            ).observe(
                viewLifecycleOwner, { viewModel.filteredProperties.postValue(it)})
            println(it)
            println(viewModel.filteredProperties)

            val searchResult = SearchResultFragment()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.activity_main_framelayout, searchResult)?.commit()
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