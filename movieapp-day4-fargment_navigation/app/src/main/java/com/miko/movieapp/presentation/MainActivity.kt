package com.miko.movieapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.miko.movieapp.R
import com.miko.movieapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            bnvMain.apply {
                setOnItemSelectedListener {
                    val fragment: Fragment = when (it.itemId) {
                        R.id.menu_movie -> {
                            MovieFragment.newInstance()
                        }
                        R.id.menu_tv_series -> {
                            TvSeriesFragment.newInstance()
                        }
                        else -> throw IllegalStateException("Menu id unknown")
                    }
                    supportFragmentManager.beginTransaction().replace(R.id.mainContainer, fragment).commit()
                    true
                }
                selectedItemId = R.id.menu_movie
            }
        }
    }
}