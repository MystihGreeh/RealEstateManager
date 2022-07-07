package com.example.realestatemanager.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.realestatemanager.databinding.FragmentSearchResultBinding
import com.example.realestatemanager.viewModel.MainActivityViewModel

class SearchResultFragment : Fragment() {

    private var bindingSearchResultFragment: FragmentSearchResultBinding? = null
    private val binding get() = bindingSearchResultFragment!!

    val viewModel : MainActivityViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingSearchResultFragment = FragmentSearchResultBinding.inflate(inflater, container, false)

        return binding.root}

}