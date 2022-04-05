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


    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainActivityViewModel by viewModels {
       ViewModelFactory((application as RealEstateManagerApplication).repository)
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.allProperties

        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.colorPrimaryDark)

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
        displayCurrentFragment(listViewFragment)
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


}


