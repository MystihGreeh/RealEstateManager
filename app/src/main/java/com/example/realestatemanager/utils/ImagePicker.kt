package com.example.realestatemanager.utils

import android.content.Intent
import android.media.MediaScannerConnection
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class ImagePicker (private val activity: AppCompatActivity, typePicture: TypePicture,
                   private val onImagePickerResult: (Uri) -> Unit) {

    private val uniqueKey = System.currentTimeMillis().toString()

    private var requestCameraPermission: ActivityResultLauncher<String>
    private var takePicture: ActivityResultLauncher<Uri>

    private val picturePath = File(activity.applicationContext.filesDir, typePicture.type)
    private lateinit var filename: String
    private lateinit var currentPhotoPath: String
    private lateinit var currentUri: Uri

    init {
        requestCameraPermission = activity.activityResultRegistry.register(
            "requestCameraPermission_$uniqueKey",
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                takePicture()
            }
        }

        takePicture = activity.activityResultRegistry.register(
            "takePicture_$uniqueKey",
            ActivityResultContracts.TakePicture()
        ) { success ->
            if (success) {
                val file = File(currentPhotoPath)
                MediaScannerConnection.scanFile(
                    activity.applicationContext, arrayOf(file.toString()),
                    null, null
                )
                onImagePickerResult(currentUri)
            }
        }
    }


    /*fun show() {
        val alertDialogBuilder = AlertDialog.Builder(activity)

        alertDialogBuilder.setTitle(activity.getString(R.string.alert_dialog_image_picker))

        val options = arrayOf(
            activity.getString(R.string.select_image),
            activity.getString(R.string.take_picture)
        )

        alertDialogBuilder.setItems(options) { _, which ->
            when (which) {
                0 -> selectImage()
                1 -> requestPermissionAndTakePicture()
            }
        }

        alertDialogBuilder.show()
    }*/

    private fun requestPermissionAndTakePicture() {
        requestCameraPermission.launch(android.Manifest.permission.CAMERA)
    }

    private fun selectImage() {
        activity.intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        activity.intent.addCategory(Intent.CATEGORY_OPENABLE)
        activity.intent.type = "image/*"
        activity.startActivityForResult(activity.intent, OPEN_DOCUMENT)
    }

    private fun takePicture() {
        currentUri = getUri()
        takePicture.launch(currentUri)
    }

    private fun getUri(): Uri {
        if (!picturePath.exists()) {
            picturePath.mkdirs()
        }



        val timestamp: String =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        filename = "${timestamp}.jpg"
        val file = File(picturePath, filename)
            .apply {
                currentPhotoPath = canonicalPath
            }

        return FileProvider.getUriForFile(
            activity,
            "com.openclassrooms.realestatemanager.fileprovider", file
        )
    }

    companion object {

        private const val OPEN_DOCUMENT = 10

        enum class TypePicture(var type: String) {
            Profile("Profile"),
            Property("Property")
        }
    }

}