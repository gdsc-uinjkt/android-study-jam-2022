package com.miko.movieapp.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.miko.movieapp.R
import com.miko.movieapp.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, SettingsActivity::class.java)
            context.startActivity(starter)
        }
    }

    private lateinit var binding: ActivitySettingsBinding
    private val sharedPreferences by lazy {
        getSharedPreferences(getString(R.string.movieapp_pref), MODE_PRIVATE)
    }
    private val settingsMap by lazy {
        mutableMapOf(
            getString(R.string.pref_language) to sharedPreferences.getString(getString(R.string.pref_language), "en") as Any
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.title_settings)
        binding.apply {
            switchDarkMode.apply {
                isChecked = sharedPreferences.getBoolean(getString(R.string.pref_dark_mode), false)
                setOnCheckedChangeListener { _, b ->
                    settingsMap[getString(R.string.pref_dark_mode)] = b
                    if (settingsMap[getString(R.string.pref_dark_mode)] as Boolean) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }

                    with(sharedPreferences.edit()) {
                        putBoolean(getString(R.string.pref_dark_mode), settingsMap[getString(R.string.pref_dark_mode)] as Boolean)
                        apply()
                    }
                }
            }
        }
    }
}