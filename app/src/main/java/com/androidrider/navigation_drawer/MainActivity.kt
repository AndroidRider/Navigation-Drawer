package com.androidrider.navigation_drawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.androidrider.navigation_drawer.Fragments.HomeFragment
import com.androidrider.navigation_drawer.Fragments.InformationFragment
import com.androidrider.navigation_drawer.Fragments.MessageFragment
import com.androidrider.navigation_drawer.Fragments.ProfileFragment
import com.androidrider.navigation_drawer.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var binding: ActivityMainBinding

    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransaction : FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setTitle("Amazon")

        fragmentManager = supportFragmentManager
        // By Default Open Open Home Fragment
        openFragment(HomeFragment())

        binding.navigationDrawer.setNavigationItemSelectedListener(this)

        // Hamburger Icon
        val toggle = ActionBarDrawerToggle(this,
            binding.drawerLayout, binding.toolbar, R.string.nav_open, R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

    }


    // Open Fragment Method
    private fun openFragment(fragment: Fragment){
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

    // When click on Navigation Item
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.nav_home-> openFragment(HomeFragment())
            R.id.nav_message-> openFragment(MessageFragment())
            R.id.nav_profile-> openFragment(ProfileFragment())
            R.id.nav_information-> openFragment(InformationFragment())

        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        when (fragmentManager.findFragmentById(R.id.fragment_container)) {
            !is HomeFragment -> {
                binding.navigationDrawer.menu.getItem(0).isChecked = true
                openFragment(HomeFragment())
            }
            else -> super.getOnBackPressedDispatcher().onBackPressed()
        }
    }
}