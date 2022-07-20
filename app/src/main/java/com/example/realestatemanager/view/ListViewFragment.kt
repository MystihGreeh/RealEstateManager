package com.example.realestatemanager.view

import android.content.Intent
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.realestatemanager.R
import com.example.realestatemanager.databinding.FragmentListBinding
import com.example.realestatemanager.model.Property
import com.example.realestatemanager.viewModel.MainActivityViewModel
import kotlin.properties.Delegates

class ListViewFragment : Fragment(), PropertyClickInterface, PropertyDeleteInterface{

    private var bindingListFragment: FragmentListBinding? = null
    private val binding get() = bindingListFragment!!
    var mIsDualPanel by Delegates.notNull<Boolean>()

    val viewModel : MainActivityViewModel by activityViewModels()


    companion object {
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingListFragment = FragmentListBinding.inflate(inflater, container, false)

        viewModel.allProperties.observe(this, {
            list -> binding.propertyListRecyclerView.adapter = PropertyAdapter(this, list, this, this)
        })

        addButtonClicked()

        return binding.root}



    private fun addButtonClicked(){
        binding.floatingActionButton.setOnClickListener{
            val intent = Intent(this@ListViewFragment.requireContext(), AddPropertyActivity::class.java)
            intent.setFlags(FLAG_GRANT_READ_URI_PERMISSION)
            startActivity(intent)
            }
    }
    override fun onDeleteClick(property: Property) {
        viewModel.deleteProperty(property)
        Toast.makeText(requireContext(), "${property.city} Deleted", Toast.LENGTH_LONG).show()
    }

    override fun onPropertyClick(property: Property) {

        Toast.makeText(requireContext(), "${property.typeOfGood} ${property.city}", Toast.LENGTH_LONG).show()

        val bundle = Bundle()

        //bundle.putString("modify", "Edit")
        bundle.putSerializable("property", property)
        val detailsFragment = PropertyDetailsFragment()
        detailsFragment.arguments = bundle

        if (view?.findViewById<FrameLayout>(R.id.details_main_framelayout)?.visibility  == View.INVISIBLE){
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.details_main_framelayout, detailsFragment)?.commit()
        } else {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.activity_main_framelayout, detailsFragment)?.commit()
        }

    }


}