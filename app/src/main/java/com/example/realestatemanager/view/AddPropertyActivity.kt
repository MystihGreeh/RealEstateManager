package com.example.realestatemanager.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.realestatemanager.BuildConfig
import com.example.realestatemanager.R
import com.example.realestatemanager.adapter.PhotoAdapter
import com.example.realestatemanager.api.RealEstateManagerApplication
import com.example.realestatemanager.databinding.ActivityAddPropertyBinding
import com.example.realestatemanager.databinding.DialogImputBinding
import com.example.realestatemanager.model.Property
import com.example.realestatemanager.model.PropertyPhoto
import com.example.realestatemanager.utils.Utils
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
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class AddPropertyActivity(): AppCompatActivity() {

    private var binding: ActivityAddPropertyBinding? = null
    private var bindingDialog: DialogImputBinding? = null
    private val CAMERA_REQUEST_CODE = 1
    private val GALLERY_REQUEST_CODE = 2
    var currentPhotoPath: String = ""
    var propertyImage: String = ""
    lateinit var fullAddressList: List<Address>
    lateinit var propertyStaticMapUrl: String
    val GOOGLE_KEY: String = BuildConfig.GOOGLE_KEY
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<PhotoAdapter.ViewHolder>? = null
    var canSave: Boolean = false
    var photoDetail: String = ""
    lateinit var fullAddress: String
    var propertyPrice: String = ""
    lateinit var propertySurface: String
    lateinit var propertyNbrRoom: String
    var photoList = ArrayList<PropertyPhoto>()



    // Initializing ViewModel
    private val viewModel: AddPropertyViewModel by viewModels {
        ViewModelFactory(
            (application as RealEstateManagerApplication).repository,
            (application as RealEstateManagerApplication).photoRepository
        )
    }



    var propertySchool: Boolean = false
    var propertyParks: Boolean = false
    var propertyParking: Boolean = false
    var propertyMarket: Boolean = false
    var propertyAllNearby: Boolean = false
    var propertyOnSale: Boolean = false
    var propertyCreatedDate: String = ""
    var propertyDateOfSale: String = ""
    var propertyBus: Boolean = false
    lateinit var propertySeller: String
    var propertyType: String = ""
    lateinit var geocoder: Geocoder
    var latitude: Double = 0.0
    var longitude: Double = 0.0

    companion object {
        val IMAGE_REQUEST_CODE = 100
    }


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_property)
        binding = ActivityAddPropertyBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        modifyProperty()

        takeCoverPictureListener()
        selectSpinner()

        binding?.saveButton?.setOnClickListener {
            binding?.saveButton?.setText("Save Property")
            handlingErrors()
            if (canSave) {
                CoroutineScope(Main).launch {
                    saveProperty()
                }
            }
            else{
                Toast.makeText(this, R.string.required_field, Toast.LENGTH_LONG).show()
            }

        }

    }

    //--------------------------------------------------------------------------------------------//
    //-------------------------- SELECTING INFO AND SAVING PROPERTY ------------------------------//
    //--------------------------------------------------------------------------------------------//

    fun selectSpinner() {
        if (binding?.propertyType != null) {
            val adapter = ArrayAdapter.createFromResource(
                this, R.array.type_array,
                android.R.layout.simple_spinner_item
            )
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            binding?.propertyType?.adapter = adapter
        }

        binding?.propertyType?.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent!!.getItemAtPosition(position)
                propertyType = selectedItem as String
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        })

        if (binding?.sellerSpinner != null) {
            val adapter = ArrayAdapter.createFromResource(
                this, R.array.seller_array,
                android.R.layout.simple_spinner_item
            )
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            binding?.sellerSpinner?.adapter = adapter
        }

        binding?.sellerSpinner?.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(

                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent!!.getItemAtPosition(position)
                propertySeller = selectedItem as String
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        })
    }

    private fun modifyProperty(){
        var property: Property? = intent.getSerializableExtra("property") as Property?

        if (property?.school != true){
            binding?.schoolCheck?.isChecked
        }
        println(property?.priceInDollars)

            propertyType = property?.typeOfGood.toString()
        println(property?.priceInDollars.toString())
            propertyPrice = property?.priceInDollars.toString()
            propertyNbrRoom = property?.numberOfRoom.toString()
            propertySurface = property?.surfaceInMeters.toString()


    }


    private fun saveProperty() {


        var propertyPriceText = binding?.price?.text.toString()
        propertyPriceText.get(propertyPrice.toInt())
        propertySurface = binding?.surface?.text.toString ()
        propertyNbrRoom = binding?.autoCompleteRooms?.text.toString()
        val propertyNbrBedroom = binding?.autoCompleteBedrooms?.text.toString()
        val propertyDescription = binding?.description?.text.toString()
        val propertyStreet = binding?.streetAddress?.text.toString()
        val propertyPostalCode = binding?.postalCode?.text.toString()
        val propertyCity = binding?.city?.text.toString()
        val propertyCountry = binding?.contry?.text.toString()
        propertyCreatedDate = Utils.Companion.getTodayDate()!!
        //Setting the Geocoder and getting the StaticMap URL if Internet is available
        if (!Utils.Companion.isInternetAvailable(this) || !Utils.Companion.isWifiAvailable(this)){
            fullAddressList = emptyList()
            latitude = 0.0
            longitude =  0.0
            propertyStaticMapUrl = ""
            fullAddress = null.toString()
        } else{
            geocoder = Geocoder(this, Locale.getDefault()) // initializing Geocoder
         fullAddress = "$propertyStreet, $propertyPostalCode, $propertyCity, $propertyCountry"
        fullAddressList = geocoder.getFromLocationName(fullAddress, 1)
        latitude = fullAddressList[0].latitude
        longitude = fullAddressList[0].longitude

        propertyStaticMapUrl =
            "http://maps.google.com/maps/api/staticmap?center=" + latitude + "," + longitude + "&zoom=15&size=200x200&sensor=false&key=" + GOOGLE_KEY}


        //Saving property using ViewModel
        viewModel.addProperty(
            Property(
                null,
                propertyType,
                propertyPrice.toInt(),
                propertySurface.toInt(),
                propertyNbrRoom.toInt(),
                propertyNbrBedroom.toInt(),
                propertyDescription,
                propertyStreet,
                propertyPostalCode,
                propertyCity,
                propertyCountry,
                propertyBus,
                propertyMarket,
                propertyParks,
                propertyParking,
                propertySchool,
                propertyAllNearby,
                propertyOnSale,
                propertySeller,
                propertyImage,
                propertyCreatedDate,
                propertyDateOfSale,
                fullAddress,
                longitude,
                latitude,
                propertyStaticMapUrl
            )
        ).observe(this, {
            CoroutineScope(Main).launch {
                createAllPhotos(it)
            }
        })
        this.finish()
    }

    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked
            //When user pick a nearby place(s)
            when (view.id) {
                R.id.is_sold_check -> {
                    propertyOnSale = checked
                    propertyDateOfSale = Utils.Companion.getTodayDate()!!
                }
                R.id.bus_check -> {
                    propertyBus = checked
                }
                R.id.market_check -> {
                    propertyMarket = checked
                }
                R.id.parking_check -> {
                    propertyParking = checked
                }
                R.id.parks_check -> {
                    propertyParks = checked
                }
                R.id.school_check -> {
                    propertySchool = checked
                }
                R.id.all_check -> {
                    propertyAllNearby = checked
                    propertyBus = checked
                    propertyMarket = checked
                    propertyParking = checked
                    propertyParks = checked
                    propertySchool = checked
                }
            }
        }
    }

    //--------------------------------------------------------------------------------------------//
    //----------------------------------------- PHOTOS -------------------------------------------//
    //--------------------------------------------------------------------------------------------//

    private fun initiateRecyclerView() {
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding?.addHorizontalRecyclerView?.layoutManager = layoutManager
        adapter = PhotoAdapter(viewModel.propertyPhotos)
        binding?.addHorizontalRecyclerView?.adapter = adapter
    }

    fun takeCoverPictureListener() {
        binding?.addPictureCamera?.setOnClickListener {
            cameraCheckPermission()

        }
        binding?.addPictureGallery?.setOnClickListener {
            galleryCheckPermission()}
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
                p0: PermissionRequest?, p1: PermissionToken?
            ) {
                showRotationalDialogForPermission()
            }
        }).onSameThread().check()
    }

    private fun gallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/"
        //propertyImage = intent.data.toString()
        checkFileRights()
        requestFileRights(true)
        startActivityForResult(intent, GALLERY_REQUEST_CODE)

    }


    private fun cameraCheckPermission() {
        Dexter.withContext(this)
            .withPermissions(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA
            ).withListener(
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
                        p1: PermissionToken?
                    ) {
                        showRotationalDialogForPermission()
                    }
                }
            ).onSameThread().check()
    }

    private fun camera() {
        var photoFile: File? = null
        try {
            photoFile = createImageFile()
        } catch (ex: IOException) {
        }
        if (photoFile != null) {
            // Continue only if the File was successfully created
            val photoURI: Uri = FileProvider.getUriForFile(
                this,
                "com.example.android.fileProvider",
                photoFile
            )
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, CAMERA_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                CAMERA_REQUEST_CODE -> {
                    Toast.makeText(this, "Photo Added", Toast.LENGTH_LONG).show()
                    val bitmap = data?.extras?.get("data") as Bitmap
                    bitmap.saveImage(this)
                    intent.addFlags(FLAG_GRANT_READ_URI_PERMISSION)
                    intent.addFlags(FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
                    showDetailsTextDialog()
                }
                GALLERY_REQUEST_CODE -> {

                    Toast.makeText(this, "Photo Added", Toast.LENGTH_LONG).show()
                    val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(
                        this.getContentResolver(),
                        Uri.parse(data?.data.toString())
                    )
                    bitmap.saveImage(this)
                    intent.addFlags(FLAG_GRANT_READ_URI_PERMISSION)
                    intent.addFlags(FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
                    showDetailsTextDialog()
                }
            }

        }

    }


    // Save photo description
    private fun showDetailsTextDialog() {
        val builder = AlertDialog.Builder(this)
        bindingDialog = DialogImputBinding.inflate(layoutInflater)
        builder.setView(bindingDialog!!.root)
        val mAlertDialog = builder.create()
        mAlertDialog.show()
        bindingDialog!!.savePhotoDetails.setOnClickListener {
            photoDetail = bindingDialog!!.photoDetailsTextView.text.toString()
            mAlertDialog.dismiss()
            Toast.makeText(this, R.string.detail_saved, Toast.LENGTH_LONG).show()
            createPhoto()
        }



    }


    private fun showRotationalDialogForPermission() {
        AlertDialog.Builder(this)
            .setMessage(
                "It looks like you have turned off permissions"
                        + "required for this feature. It can be enable under App settings"
            )
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
        val image = File.createTempFile(
            "Property_image_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        )
        currentPhotoPath = image.absolutePath
        return image
    }


    @SuppressLint("SimpleDateFormat")
    fun Bitmap.saveImage(context: Context): Uri? {
        val realEstateManager = "Pictures/realEstateManager"
        val date = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageName = "match_$date.jpg"
        val bytes = ByteArrayOutputStream()
        this.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        if (bytes.size() == 0)
            return null

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            val path: String =
                MediaStore.Images.Media.insertImage(context.contentResolver, this, imageName, null)
            return Uri.parse(path)
        }
        val resolver = context.contentResolver

        val pictureCollection =
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)

        val pictureDetails = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, imageName)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
            put(MediaStore.MediaColumns.RELATIVE_PATH, realEstateManager)
            put(MediaStore.MediaColumns.IS_PENDING, 1)
        }

        val uri = resolver.insert(pictureCollection, pictureDetails)
        if (uri != null) {
            resolver.openFileDescriptor(uri, "w", null).use { pfd ->
                if (pfd != null) {
                    val op = FileOutputStream(pfd.fileDescriptor)
                    op.write(bytes.toByteArray())
                    op.close()
                }
            }
            pictureDetails.clear()
            pictureDetails.put(MediaStore.Audio.Media.IS_PENDING, 0)
            resolver.update(uri, pictureDetails, null, null)
        }
        propertyImage = uri.toString()
        return uri
    }

    fun checkFileRights(): Boolean {
        val result = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )
        return result == PackageManager.PERMISSION_GRANTED
    }

    fun requestFileRights(share: Boolean) {
        val code = if (share) 101 else 102
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            code
        )
    }


    private fun createAllPhotos(id: Long) {
        viewModel.propertyPhotos.forEachIndexed { index, element ->
            viewModel.addPhoto(
                PropertyPhoto(
                    id,
                   element.name,
                   element.description
                )
            )
        }
    }

    private fun createPhoto() {
                viewModel.addNewPhoto(
                    PropertyPhoto(
                        0,
                        propertyImage,
                        photoDetail
                    ))
        initiateRecyclerView()
    }

    //--------------------------------------------------------------------------------------------//
    //----------------------------------------- HANDLING ERRORS ----------------------------------//
    //--------------------------------------------------------------------------------------------//

    private fun handlingErrors() {

        when {
            binding?.streetAddress?.text.isNullOrEmpty() || binding?.city?.text.isNullOrEmpty() || binding?.contry?.text.isNullOrEmpty() || binding?.price?.text.isNullOrEmpty() -> {
                canSave = false
                if (binding?.streetAddress?.text.isNullOrEmpty()) {
                    binding?.streetAddressLayout?.error =
                        resources.getString(R.string.required_field)
                }
                if (binding?.city?.text.isNullOrEmpty()) {
                    binding?.cityLayout?.error =
                        resources.getString(R.string.required_field)
                }
                if (binding?.contry?.text.isNullOrEmpty()) {
                    binding?.contryLayout?.error =
                        resources.getString(R.string.required_field)
                }
                if (binding?.price?.text.isNullOrEmpty()) {
                    binding?.priceLayout?.error =
                        resources.getString(R.string.required_field)
                }
            }
            else -> {
                canSave = true
                binding?.streetAddressLayout?.error = null
                binding?.cityLayout?.error = null
                binding?.contryLayout?.error = null
                binding?.priceLayout?.error = null
            }
        }
    }
}


