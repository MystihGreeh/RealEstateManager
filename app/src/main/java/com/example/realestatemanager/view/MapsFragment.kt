package com.example.realestatemanager.view

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.realestatemanager.R
import com.example.realestatemanager.databinding.MapsFragmentBinding
import com.example.realestatemanager.viewModel.MainActivityViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment(R.layout.maps_fragment), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var mapBinding: MapsFragmentBinding? = null
    private val binding get() = mapBinding!!
    private lateinit var fusedLocationClient: FusedLocationProviderClient




    val viewModel : MainActivityViewModel by activityViewModels()

    companion object {
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mapBinding = MapsFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    @SuppressLint("MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapBinding?.map?.onCreate(savedInstanceState)
        mapBinding?.map?.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        getDeviceLocation()


    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        uiSettings()
        // Add a marker for each properties
        viewModel.allProperties.observe(this, {
            propertyList ->
            if (propertyList != null){
                val property1 = LatLng(propertyList[0].latitude!!, propertyList[0].longitude!!)
                val update = CameraUpdateFactory.newLatLngZoom(property1, 12F)
                mMap.moveCamera(update)

                propertyList.forEach{
                    val marker = LatLng(it.latitude!!, it.longitude!!)
                    mMap.addMarker(MarkerOptions().position(marker).title(it.street + ", " + it.city))
                    mMap.addMarker(MarkerOptions().position(marker).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)))
                        ?.tag = it.id
                }
            }
        })

    }


    fun uiSettings() {
        mMap.setMinZoomPreference(12f)
        mMap.isIndoorEnabled = true
        val uiSettings = mMap.uiSettings
        uiSettings.isMyLocationButtonEnabled = true
        uiSettings.isCompassEnabled = true
        uiSettings.isZoomControlsEnabled = true
        uiSettings.isMyLocationButtonEnabled
    }

    // Find device location
    fun getDeviceLocation() {
        try {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    val currentLatLng = LatLng(latitude, longitude)
                    val cameraUpdate = CameraUpdateFactory.newLatLngZoom(currentLatLng, 8F)
                    mMap.animateCamera(cameraUpdate)
                    mMap.addMarker(MarkerOptions().position(currentLatLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)))
                }
            }
        } catch (error: SecurityException) {}
    }

    // LIFE STATE MAP

    override fun onResume() {
        super.onResume()
        mapBinding?.map?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapBinding?.map?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapBinding?.map?.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapBinding?.map?.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapBinding?.map?.onLowMemory()
    }

    override fun onStart() {
        super.onStart()
        mapBinding?.map?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapBinding?.map?.onStop()
    }
}