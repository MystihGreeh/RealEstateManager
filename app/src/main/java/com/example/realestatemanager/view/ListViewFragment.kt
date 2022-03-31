package com.example.realestatemanager.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.realestatemanager.R
import com.example.realestatemanager.databinding.FragmentListBinding

class ListViewFragment : Fragment() {


    private var bindingListFragment: FragmentListBinding? = null
    private val binding get() = bindingListFragment!!

    //private var viewModel : AddPropertyViewModel by viewModels<AddPropertyViewModel>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindingListFragment = FragmentListBinding.inflate(inflater, container, false)

        //setting the adapter
        val propertyAdapter = PropertyAdapter(R.layout.property_rv_items)
        binding.propertyListRecyclerView.adapter = propertyAdapter


        /*viewModel.allProperties.observe(
            viewLifecycleOwner
        ) { list -> list.let { propertyAdapter.updateList(it)
        }}
       viewModel.allProperties.observe(this, {
            list -> list.let { propertyAdapter.updateList(it) }
        })*/

        //setting the floating action button
        addButtonClicked()

        return binding.root}



    private fun addButtonClicked(){
        binding.floatingActionButton.setOnClickListener{
            val intent = Intent(this@ListViewFragment.requireContext(), AddPropertyActivity::class.java)
            startActivity(intent)
            }
    }
}