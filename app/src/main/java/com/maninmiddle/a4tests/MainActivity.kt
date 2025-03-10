package com.maninmiddle.a4tests

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.maninmiddle.a4tests.databinding.ActivityMainBinding
import com.maninmiddle.core.common.util.MainActivityFragmentContract
import com.maninmiddle.features.home.HomeFragment

class MainActivity : AppCompatActivity(), MainActivityFragmentContract {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment(), false)
        }

    }


    override fun replaceFragment(fragment: Fragment, addToBackStack: Boolean) {
        val supportFragmentManager = supportFragmentManager.beginTransaction()
        supportFragmentManager.replace(binding.frameLayout.id, fragment)
        supportFragmentManager.setCustomAnimations(
            R.anim.slide_out_left,
            R.anim.slide_in_right,
            R.anim.slide_out_left,
            R.anim.slide_in_right
        )
        if (addToBackStack)
            supportFragmentManager.addToBackStack(null)
        supportFragmentManager.commit()
    }
}