package com.miko.movieapp.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_about -> AboutActivity.start(this)
            R.id.menu_settings -> SettingsActivity.start(this)
            else -> throw IllegalStateException("Menu id unknown")
        }
        return super.onOptionsItemSelected(item)
    }
}