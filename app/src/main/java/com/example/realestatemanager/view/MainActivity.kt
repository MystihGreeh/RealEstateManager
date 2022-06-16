package com.example.realestatemanager.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.realestatemanager.R
import com.example.realestatemanager.api.RealEstateManagerApplication
import com.example.realestatemanager.databinding.ActivityMainBinding
import com.example.realestatemanager.viewModel.MainActivityViewModel
import com.example.realestatemanager.viewModel.ViewModelFactory



class MainActivity : AppCompatActivity() {

    //FRAGMENTS
    private var listViewFragment = ListViewFragment()
    private val searchFragment = SearchFragment()
    private val loanFragment = LoanFragment()
    private val mapFragment = MapsFragment()
    val LISTVIEW_FRAGMENT_ID = 3
    val SEARCH_FRAGMENT_ID = 4
    val DETAILS_FRAGMENT_ID = 5
    val LOAN_FRAGMENT_ID = 6
    val MAP_FRAGMENT_ID = 7
    private var detailsFragment = PropertyDetailsFragment()

    internal lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainActivityViewModel by viewModels {
       ViewModelFactory((application as RealEstateManagerApplication).repository, (application as RealEstateManagerApplication).photoRepository)
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.allProperties

        configureBottomView()
        showFirstFragment()
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)


    }


    // -------------------------------------------------------------------------------------------
    //                                  DISPLAYING FRAGMENTS
    // -------------------------------------------------------------------------------------------

    private fun configureBottomView() {
        //Implementing bottom view and displaying first fragment
        binding.activityMainBottomview.setOnNavigationItemSelectedListener {
                item: MenuItem ->
                updateMainFragment(
                    item.itemId
                )
            true
        }
    }
    private fun updateMainFragment(integer: Int): Boolean {
        when (integer) {
            R.id.map -> showFragment(MAP_FRAGMENT_ID)
            R.id.list_view -> showFragment(LISTVIEW_FRAGMENT_ID)
            R.id.search -> showFragment(SEARCH_FRAGMENT_ID)
            R.id.loan -> showFragment(LOAN_FRAGMENT_ID)
        }
        return true
    }

    private fun showFragment(fragmentIdentifier: Int) {
        when (fragmentIdentifier) {
            LISTVIEW_FRAGMENT_ID -> displayListFragment()
            MAP_FRAGMENT_ID -> displayMapFragment()
            SEARCH_FRAGMENT_ID -> displaySearchFragment()
            LOAN_FRAGMENT_ID -> displayLoanFragment()
            else -> {}
        }
    }

    private fun displayListFragment() {
        if(binding.detailsMainFramelayout != null){
            supportFragmentManager.beginTransaction()
                .add(R.id.activity_main_framelayout, listViewFragment)
                .commit()
            binding.detailsMainFramelayout?.visibility = View.VISIBLE
        } else {
            supportFragmentManager.beginTransaction()
                .add(R.id.activity_main_framelayout, listViewFragment)
                .commit()
        }

    }

    private fun displayMapFragment() {
        if(binding.detailsMainFramelayout != null){
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.activity_main_framelayout, mapFragment)
                commit()}
            binding.detailsMainFramelayout?.visibility = View.GONE
        } else {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.activity_main_framelayout, mapFragment)
                    commit()
                }
        }
    }

    private fun displaySearchFragment() {
        if(binding.detailsMainFramelayout != null){
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.activity_main_framelayout, searchFragment)
                commit()}
            binding.detailsMainFramelayout?.visibility = View.GONE
        } else {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.activity_main_framelayout, searchFragment)
                commit()
            }
        }
    }

    private fun displayLoanFragment() {
        if(binding.detailsMainFramelayout != null){
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.activity_main_framelayout, loanFragment)
                commit()}
            binding.detailsMainFramelayout?.visibility = View.GONE
        } else {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.activity_main_framelayout, loanFragment)
                commit()
            }
        }
    }

    // Show first fragment when activity is created
    private fun showFirstFragment() {
        val visibleFragment =
            supportFragmentManager.findFragmentById(R.id.activity_main_framelayout)
        if (visibleFragment == null) // 1.1 - Show Listview Fragment
            showFragment(LISTVIEW_FRAGMENT_ID)
        // 1.2 - Mark as selected the menu item corresponding to NewsFragment
        binding.activityMainBottomview.getMenu().getItem(0).setChecked(true)
    }




    // -------------------------------------------------------------------------------------------
    //                               HANDLING THE SCREEN ROTATION
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


