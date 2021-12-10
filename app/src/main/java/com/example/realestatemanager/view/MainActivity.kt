package com.example.realestatemanager.view

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.realestatemanager.R
import com.example.realestatemanager.databinding.MapFragmentBinding
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    //FRAGMENTS
    private val listViewFragment = ListViewFragment()
    private val searchFragment = SearchFragment()
    private val loanFragment = LoanFragment()
    private val mapFragment = MapFragment()
    private val addAgentFragment = AddAgentFragment()
    private val addPropertyFragment = AddPropertyFragment()

    lateinit var bottomView: BottomNavigationView



    // FLOATING ACTION BUTTON ANIMATION
    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_open_animation) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_close_animation) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.from_bottom_animation) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.to_bottom_animation) }

    lateinit var floatingActionButton: FloatingActionButton
    lateinit var addAgentFab: FloatingActionButton
    lateinit var addPropertyFab: FloatingActionButton
    lateinit var addAgentText: TextView
    lateinit var addPropertyText: TextView


    private var clicked = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.colorPrimaryDark)
        }

        //Implementing bottom view and displaying first fragment
        bottomView = findViewById(R.id.activity_main_bottomview)

        bottomView.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.list_view -> displayCurrentFragment(listViewFragment)
                R.id.map -> displaymapFragment(mapFragment)
                R.id.search -> displayCurrentFragment(searchFragment)
                R.id.loan -> displayCurrentFragment(loanFragment)
            }
            true
        }

        displayCurrentFragment(listViewFragment)

        //Floating action button and their texts declaration
        floatingActionButton = findViewById(R.id.floating_action_button)
        addAgentFab = findViewById(R.id.add_agent_fab)
        addAgentText = findViewById(R.id.add_agent_texte)
        addPropertyFab = findViewById(R.id.add_property_fab)
        addPropertyText = findViewById(R.id.add_property_texte)

        //Managing onClickListener for floating action button
        floatingActionButton.setOnClickListener {onAddButtonClicked()}
        addAgentFab.setOnClickListener {displayAddAgent()}
        addPropertyFab.setOnClickListener {displayAddProperty()}

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
        if (!clicked){
            addAgentFab.visibility = View.VISIBLE
            addPropertyFab.visibility = View.VISIBLE
            addAgentText.visibility = View.VISIBLE
            addPropertyText.visibility = View.VISIBLE
        } else{
            addAgentFab.visibility = View.INVISIBLE
            addPropertyFab.visibility = View.INVISIBLE
            addAgentText.visibility = View.INVISIBLE
            addPropertyText.visibility = View.INVISIBLE
        }
    }

    private fun setAnimation(clicked: Boolean) {
        if(!clicked) {
            addAgentFab.startAnimation(fromBottom)
            addPropertyFab.startAnimation(fromBottom)
            addAgentText.startAnimation(fromBottom)
            addPropertyText.startAnimation(fromBottom)
            floatingActionButton.startAnimation(rotateOpen)
        } else{
            addAgentFab.startAnimation(toBottom)
            addPropertyFab.startAnimation(toBottom)
            addAgentText.startAnimation(toBottom)
            addPropertyText.startAnimation(toBottom)
            floatingActionButton.startAnimation(rotateClose)
        }
    }


    // -------------------------------------------------------------------------------------------
    //                       DISPLAYING FIRST FRAGMENT AND FAB SPECIFIC FRAGMENTS
    // -------------------------------------------------------------------------------------------

    private fun displayCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.activity_main_framelayout, fragment)
            commit()
        }
    }
    private fun displaymapFragment(mapFragment: MapFragment){
        val mapFragment = SupportMapFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.activity_main_framelayout, mapFragment)
            .commit()
    }

    private fun displayAddAgent(){
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


