package com.example.realestatemanager.view

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.view.View
import android.widget.AdapterView
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.realestatemanager.R
import com.example.realestatemanager.api.RealEstateManagerApplication
import com.example.realestatemanager.databinding.ActivityAddPropertyBinding
import com.example.realestatemanager.model.Property
import com.example.realestatemanager.viewModel.AddPropertyViewModel
import com.example.realestatemanager.viewModel.ViewModelFactory
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class AddPropertyActivity: AppCompatActivity() {

    private var binding: ActivityAddPropertyBinding? = null
    private val CAMERA_REQUEST_CODE = 1
    private val GALLERY_REQUEST_CODE = 2
    lateinit var currentPhotoPath: String

    val seller = arrayOf("Charlotte", "David", "Sylvain", "Christelle")
    val typeOfGood = arrayOf("Type", "House", "Flat", "Duplex", "Penthouse", "Townhouse")

    private val viewModel: AddPropertyViewModel by viewModels {
        ViewModelFactory((application as RealEstateManagerApplication).repository)
    }
    var propertyID = -1
    var propertyBus = "none"
    var propertyParks = "none"
    var propertyParking = "none"
    var propertySchool = "none"
    var propertyMarket = "none"
    var propertyAllNearby = "none"

    companion object {
        val IMAGE_REQUEST_CODE = 100
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_property)
        binding = ActivityAddPropertyBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        var propertyType = "none"
        binding?.propertyType?.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedTypeOfGood = typeOfGood[position]
                propertyType = selectedTypeOfGood
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        })
        var propertySeller = "none"
        binding?.sellerSpinner?.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(

                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedSeller = seller[position]
                propertySeller = selectedSeller
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                
            }
        })
        binding?.addPictureCamera?.setOnClickListener {
            cameraCheckPermission()
        }
        binding?.addPictureGallery?.setOnClickListener {
            galleryCheckPermission()
        }


        binding?.saveButton?.setOnClickListener {
            binding?.saveButton?.setText("Save Property")
            val propertyPrice = binding?.price?.text.toString()
            val propertySurface = binding?.surface?.text.toString()
            val propertyNbrRoom = binding?.autoCompleteRooms?.text.toString()
            val propertyNbrBedroom = binding?.autoCompleteBedrooms?.text.toString()
            val propertyDescription = binding?.description?.text.toString()
            val propertyStreet = binding?.streetAddress?.text.toString()
            val propertyPostalCode = binding?.postalCode?.text.toString()
            val propertyCity = binding?.city?.text.toString()
            val propertyCountry = binding?.contry?.text.toString()
            propertyBus = if (binding?.busCheck?.isChecked == true) {"bus"} else "none"
            propertyParks = if (binding?.parksCheck?.isChecked == true) {"Park"} else "none"
            propertySchool = if (binding?.schoolCheck?.isChecked == true) {"School"} else "none"
            propertyParking = if (binding?.parkingCheck?.isChecked == true) {"Parking"} else "none"
            propertyMarket = if (binding?.marketCheck?.isChecked == true) {"Market"} else "none"
            propertyAllNearby = if (binding?.allCheck?.isChecked == true) {"Nearby all"} else "none"

            viewModel.addProperty(
                Property(
                    propertyType,
                    propertyPrice,
                    propertySurface,
                    propertyNbrRoom,
                    propertyNbrBedroom,
                    propertyDescription,
                    propertyStreet,
                    propertyPostalCode,
                    propertyCity,
                    propertyCountry,
                    propertyBus,
                    propertyMarket,
                    propertyParking,
                    propertyParks,
                    propertySchool,
                    propertyAllNearby,
                    propertySeller,
                )
            )
            Toast.makeText(this, "Property Added", Toast.LENGTH_LONG).show()

            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }


    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked
            //When user pick a nearby place(s)
            when (view.id) {
                R.id.bus_check -> {propertyBus = if (checked) {"Bus"} else {"None"}}
                R.id.market_check -> {propertyMarket = if (checked) {"Market"} else {"None"}}
                R.id.parking_check -> {propertyParking = if (checked) {"Parking"} else {"None"}}
                R.id.parks_check -> {propertyParks = if (checked) {"Parks"} else {"None"}}
                R.id.school_check -> {propertySchool = if (checked) {"School"} else {"None"}}
                R.id.all_check -> {propertyAllNearby = if (checked) {"Nearby All"} else {"None"}}
            }
        }
    }

    private fun galleryCheckPermission() {
        Dexter.withContext(this).withPermission(
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        ).withListener(object : PermissionListener {
            override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                gallery()
            }
            override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                Toast.makeText(
                    this@AddPropertyActivity,
                    "You have denied the storage permission to select image",
                    Toast.LENGTH_SHORT
                ).show()
                showRotationalDialogForPermission()
            }
            override fun onPermissionRationaleShouldBeShown(
                p0: PermissionRequest?, p1: PermissionToken?) {
                showRotationalDialogForPermission()
            }
        }).onSameThread().check()
    }

    private fun gallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }


    private fun cameraCheckPermission() {
        Dexter.withContext(this)
            .withPermissions(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA).withListener(
                object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                        report?.let {

                            if (report.areAllPermissionsGranted()) {
                                camera()
                            }
                        }
                    }
                    override fun onPermissionRationaleShouldBeShown(
                        p0: MutableList<PermissionRequest>?,
                        p1: PermissionToken?) {
                        showRotationalDialogForPermission()
                    }
                }
            ).onSameThread().check()
    }

    private fun camera() {
        val photoFile: File? = try {
            createImageFile()
        } catch (ex: IOException) {
            null
        }
        // Continue only if the File was successfully created
        photoFile?.also {
            val photoURI: Uri = FileProvider.getUriForFile(
                this,
                "com.example.android.fileprovider",
                it
            )
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, CAMERA_REQUEST_CODE)




        }
    }


    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                CAMERA_REQUEST_CODE -> {
                    val bitmap = data?.extras?.get("data") as Bitmap
                    //we are using coroutine image loader (coil)
                    binding?.horizontalRecyclerView?.setImageBitmap(bitmap)
                    binding?.horizontalRecyclerView?.cropToPadding
                }
                GALLERY_REQUEST_CODE -> {
                    binding?.horizontalRecyclerView?.setImageURI(data?.data)
                    binding?.horizontalRecyclerView?.cropToPadding
                }
            }
        }
    }*/


    private fun showRotationalDialogForPermission() {
        AlertDialog.Builder(this)
            .setMessage("It looks like you have turned off permissions"
                    + "required for this feature. It can be enable under App settings")
            .setPositiveButton("Go TO SETTINGS") { _, _ ->
                try {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)

                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
            }
            .setNegativeButton("CANCEL") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }


    //Creating a file to save pictures
    @SuppressLint("SimpleDateFormat")
    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }
}
