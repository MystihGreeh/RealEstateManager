package com.example.realestatemanager.view

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.realestatemanager.R
import com.example.realestatemanager.api.RealEstateManagerApplication
import com.example.realestatemanager.databinding.ActivityMainBinding
import com.example.realestatemanager.viewModel.MainActivityViewModel
import com.example.realestatemanager.viewModel.ViewModelFactory





class MainActivity : AppCompatActivity() {

    //FRAGMENTS
    private val listViewFragment = ListViewFragment()
    private val searchFragment = SearchFragment()
    private val loanFragment = LoanFragment()
    private val mapFragment = MapsFragment()
    private val detailsFragment = PropertyDetailsFragment()
    val BUNDLE_FRAGMENT_POSITION = "BUNDLE_FRAGMENT_POSITION"


    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainActivityViewModel by viewModels {
       ViewModelFactory((application as RealEstateManagerApplication).repository, (application as RealEstateManagerApplication).photoRepository)
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mainViewModel.allProperties

        val visibleFragment =
            supportFragmentManager.findFragmentById(R.id.main_activity_layout)
        if (visibleFragment == null){
            supportFragmentManager.beginTransaction().apply {
                show(listViewFragment)
                commit()
            }
        }
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        //displayFirstFragment()

        //Implementing bottom view and displaying first fragment
        binding.activityMainBottomview.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.list_view -> displayCurrentFragment(listViewFragment)
                R.id.map -> displayCurrentFragment(mapFragment)
                R.id.search -> displayCurrentFragment(searchFragment)
                R.id.loan -> displayCurrentFragment(loanFragment)
            }
            true
        }

    }




    // -------------------------------------------------------------------------------------------
    //                       DISPLAYING FIRST FRAGMENT
    // -------------------------------------------------------------------------------------------

    private fun displayCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.activity_main_framelayout, fragment)
            commit()
        }
    }

    private fun displayFirstFragment() {


    }


    // -------------------------------------------------------------------------------------------
    //                       HANDLING THE SCREEN ROTATION
    // -------------------------------------------------------------------------------------------



    override fun onResume() {

        super.onResume()
        binding.mainActivityLayout
    }

    override fun onPause() {
        super.onPause()
        binding.mainActivityLayout
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mainActivityLayout
    }




}


