package com.example.realestatemanager.view

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.realestatemanager.R
import com.example.realestatemanager.databinding.ActivityMainBinding
import com.google.android.gms.maps.SupportMapFragment

class MainActivity : AppCompatActivity() {

    //FRAGMENTS
    private val listViewFragment = ListViewFragment()
    private val searchFragment = SearchFragment()
    private val loanFragment = LoanFragment()
    private val mapFragment = MapsFragment()
    private val addAgentFragment = AddAgentFragment()
    private val addPropertyFragment = AddPropertyFragment()

    private lateinit var binding: ActivityMainBinding



    // FLOATING ACTION BUTTON ANIMATION
    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_open_animation) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_close_animation) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.from_bottom_animation) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.to_bottom_animation) }



    private var clicked = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.colorPrimaryDark)

        //Implementing bottom view and displaying first fragment

        binding.activityMainBottomview.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.list_view -> displayCurrentFragment(listViewFragment)
                R.id.map -> displaymapFragment(mapFragment)
                R.id.search -> displayCurrentFragment(searchFragment)
                R.id.loan -> displayCurrentFragment(loanFragment)
            }
            true
        }
        displayCurrentFragment(listViewFragment)


    }



    // -------------------------------------------------------------------------------------------
    //                         SETTINGS FLOATING ACTION BUTTON ACTIONS
    // -------------------------------------------------------------------------------------------

    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked

    }


    private fun setVisibility(clicked: Boolean) {
            if (!clicked) {
                binding.addAgentFab.visibility = View.VISIBLE
                binding.addPropertyFab.visibility = View.VISIBLE
                binding.addAgentTexte.visibility = View.VISIBLE
                binding.addPropertyTexte.visibility = View.VISIBLE
            } else {
                binding.addAgentFab.visibility = View.INVISIBLE
                binding.addPropertyFab.visibility = View.INVISIBLE
                binding.addAgentTexte.visibility = View.INVISIBLE
                binding.addPropertyTexte.visibility = View.INVISIBLE
            }
    }

    private fun setAnimation(clicked: Boolean) {
        if(!clicked) {
            binding.addAgentFab.startAnimation(fromBottom)
            binding.addPropertyFab.startAnimation(fromBottom)
            binding.addAgentTexte.startAnimation(fromBottom)
            binding.addPropertyTexte.startAnimation(fromBottom)
            binding.floatingActionButton.startAnimation(rotateOpen)
        } else{
            binding.addAgentFab.startAnimation(toBottom)
            binding.addPropertyFab.startAnimation(toBottom)
            binding.addAgentTexte.startAnimation(toBottom)
            binding.addPropertyTexte.startAnimation(toBottom)
            binding.floatingActionButton.startAnimation(rotateClose)
        }
    }

    private fun showFab(){
        binding.floatingActionButton.visibility = View.VISIBLE
        setAnimation(!clicked)
        //Managing onClickListener for floating action button
        binding.floatingActionButton.setOnClickListener {onAddButtonClicked()}
        binding.addAgentFab.setOnClickListener {displayAddAgent()}
        binding.addPropertyFab.setOnClickListener {displayAddProperty()}
    }

    private fun hideFab() {
        binding.floatingActionButton.visibility = View.INVISIBLE

    }


    // -------------------------------------------------------------------------------------------
    //                       DISPLAYING FIRST FRAGMENT AND FAB SPECIFIC FRAGMENTS
    // -------------------------------------------------------------------------------------------

    private fun displayCurrentFragment(fragment: Fragment) {
        showFab()
        setVisibility(!clicked)
        setAnimation(!clicked)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.activity_main_framelayout, fragment)
            commit()
        }
    }

    private fun displaymapFragment(mapFragment: MapsFragment){
        setVisibility(!clicked)
        setAnimation(!clicked)
        val mapFragment = SupportMapFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.activity_main_framelayout, mapFragment)
            .commit()
    }

    private fun displayAddAgent(){
        clicked = !clicked
        setVisibility(!clicked)
        setAnimation(!clicked)
        val fragment: Fragment? = supportFragmentManager
            .findFragmentByTag(AddAgentFragment::class.java.simpleName)
        if (fragment !is AddAgentFragment) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.activity_main_framelayout, addAgentFragment,
                    AddAgentFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun displayAddProperty(){
        clicked = !clicked
        setVisibility(!clicked)
        setAnimation(!clicked)
        val fragment: Fragment? = supportFragmentManager
            .findFragmentByTag(AddPropertyFragment::class.java.simpleName)
        if (fragment !is AddAgentFragment){
            supportFragmentManager.beginTransaction()
                .replace(R.id.activity_main_framelayout, addPropertyFragment,
                    AddPropertyFragment::class.java.simpleName)
                .commit()
        }
    }

}


