package com.example.realestatemanager.view

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.realestatemanager.R
import com.example.realestatemanager.databinding.FragmentAddAgentBinding
import com.example.realestatemanager.model.Agent
import com.example.realestatemanager.utils.ImagePicker
import com.example.realestatemanager.utils.Utils
import com.example.realestatemanager.viewModel.AddAgentViewModel
import com.google.android.material.snackbar.Snackbar


class AddAgentFragment: Fragment() {

    private var bindingAgent: FragmentAddAgentBinding? = null
    private val binding get() = bindingAgent!!

    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var saveItem: MenuItem


    val viewModel = ViewModelProvider(requireActivity())[AddAgentViewModel::class.java]
    /*var addAgentViewModel: AddAgentViewModel? = ViewModelProvider(this, ViewModelFactory.getInstance).get(
        AddAgentViewModel::class.java)*/

    private var currentProfilePicture: String? = null

    private lateinit var imagePicker: ImagePicker
    private lateinit var currentUri: Uri





    companion object{
        val IMAGE_REQUEST_CODE = 100
        private const val OPEN_DOCUMENT = 10
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindingAgent = FragmentAddAgentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }


    private fun saveAgent() {

        bindingAgent?.checkButton?.setOnClickListener {


            val firstName: String = bindingAgent?.agentFirstName?.text.toString()
            val lastName: String = bindingAgent?.agentLastName?.text.toString()
            val email: String = bindingAgent?.agentEmail?.text.toString()
            val phone: String = bindingAgent?.agentPhoneNumber?.text.toString()

            bindingAgent?.agentFirstNameLayout?.isErrorEnabled = false
            bindingAgent?.agentLastNameLayout?.isErrorEnabled = false
            bindingAgent?.agentEmailLayout?.isErrorEnabled = false
            bindingAgent?.agentPhoneNumberLayout?.isErrorEnabled = false

            var isDataOk = true

            if (!Utils.Companion.validateEmail(email)) {
                bindingAgent?.agentEmailLayout?.error = getString(R.string.required_field)
                isDataOk = false
            }

            if (firstName.isBlank()) {
                bindingAgent?.agentFirstNameLayout?.error = getString(R.string.required_field)
                isDataOk = false
            }

            if (lastName.isBlank()) {
                bindingAgent?.agentLastNameLayout?.error = getString(R.string.required_field)
                isDataOk = false
            }

            if (phone.isBlank()) {
                bindingAgent?.agentPhoneNumberLayout?.error = getString(R.string.required_field)
                isDataOk = false
            }

            if (currentProfilePicture == null) {
                showSnackBar(constraintLayout, getString(R.string.required_field))
            }

            if (isDataOk) {
                doChanges(firstName, lastName, email, phone)
            }
            false
        }
    }

    private fun doChanges(firstName: String, lastName: String, email: String, phone: String) {
        val agent = Agent(
            firstName = firstName,
            lastName = lastName,
            email = email,
            phoneNumber = phone,
            //uriProfilePicture = currentProfilePicture!!
        )
        //finish()
    }

    // Show Snack Bar with a message
    private fun showSnackBar(constraintLayout: ConstraintLayout, message: String) {
        Snackbar.make(constraintLayout, message, Snackbar.LENGTH_SHORT).show()
    }


    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         super.onActivityResult(requestCode, resultCode, data)
         if (requestCode == OPEN_DOCUMENT) {
             if (resultCode == RESULT_OK) {
                 if (data != null) {
                     currentUri = data.data!!
                     var takeFlags: Int = data.flags
                     takeFlags =
                         takeFlags and (Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                     contentResolver.takePersistableUriPermission(currentUri, takeFlags)
                     grantUriPermission(
                         "com.openclassrooms.realestatemanager",
                         currentUri,
                         Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
                     )

                     Glide.with(this)
                         .load(currentUri)
                         .apply(RequestOptions.circleCropTransform())
                         .into(bindingAgent?.agentPicture!!)
                     currentProfilePicture = currentUri.toString()
                 }
             }
         }
     }*/



}


