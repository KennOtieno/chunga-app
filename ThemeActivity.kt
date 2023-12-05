package com.example.chunga_cash_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.app.AppCompatDelegate

class ThemeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme) // xml layout file

        val themeSwitch = findViewById<SwitchCompat>(R.id.themeSwitch)
        // Fetch current theme preference and set the switch state (default: LightMode)
        val isDarkModeEnabled = getThemePreferenceFromSharedPreferences()
        themeSwitch.isChecked = isDarkModeEnabled

        // Listen for switch state changes and update theme preference accordingly
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            updateThemePreferenceInSharedPreferences(isChecked)
            applyTheme(isChecked)
        }
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
}