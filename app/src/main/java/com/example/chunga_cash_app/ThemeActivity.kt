package com.example.chunga_cash_app

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.preference.PreferenceManager
import androidx.appcompat.app.AppCompatDelegate
import com.example.chunga_cash_app.databinding.ActivityThemeBinding

class ThemeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityThemeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThemeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val themeSwitch = findViewById<SwitchCompat>(R.id.themeSwitch)
        val darkButton = binding.darkButton
        val lightButton = binding.lightButton

        // Fetch current theme preference and set the switch state (default: LightMode)
        val isDarkModeEnabled = getThemePreferenceFromSharedPreferences()
        themeSwitch.isChecked = isDarkModeEnabled

        // Listen for switch state changes and update theme preference accordingly
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            updateThemePreferenceInSharedPreferences(isChecked)
            applyTheme(isChecked)
        }

        darkButton.setOnClickListener { onDarkButtonClick() }
        lightButton.setOnClickListener { onLightButtonClick() }
    }

    // Function to apply the selected Theme
    private fun applyTheme(isDarkModeEnabled: Boolean) {
        val mode = if (isDarkModeEnabled) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(mode)

        // Recreate the activity to apply the new theme immediately with a smooth transition
        recreateWithTransition()
    }

    private fun recreateWithTransition() {
        val intent = intent
        finish()
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    private fun getThemePreferenceFromSharedPreferences(): Boolean {
        val sharedPreferences: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(this)
        // "theme_preference" is the key, false is the default value (light mode)
        return sharedPreferences.getBoolean("theme_preference", false)
    }

    // Function to Update theme preference in SharedPreferences
    private fun updateThemePreferenceInSharedPreferences(isDarkModeEnabled: Boolean) {
        val sharedPreferences: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(this)
        val editor = sharedPreferences.edit()
        // "theme_preference" is the key to store the theme preference
        editor.putBoolean("theme_preference", isDarkModeEnabled)
        editor.apply()
    }

    // Method to handle Dark button click
    private fun onDarkButtonClick() {
        updateThemePreferenceInSharedPreferences(true)
        applyTheme(true)
    }

    // Method to handle Light button click
    private fun onLightButtonClick() {
        updateThemePreferenceInSharedPreferences(false)
        applyTheme(false)
    }
}