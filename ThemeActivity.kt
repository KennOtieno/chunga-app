import android.os.Bundle
import androidx.appcomapat.app.AppCompatActivity
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.app.AppCompatDelegate

class ThemeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme) // Change to xml layout file

        val themeSwitch = findViewById<SwitchComapat>(R.id.themeSwitch)
        // Fetch current theme preference and set the switch state (default: LightMode)
        val isDarkModeEnabled = getThemePreferenceFromSharedPreference()
        themeSwitch.isChecked = isDarkModeEnabled

        // Listen for switch state changes and update theme preference accolrdingly
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            updateThemePreferenceInSharedPreferences(isChecked)
            applyTheme(isChecked)
        }
    }

    // Fuuntion to apply the selected  Theme
    private fun applyTheme(isDarkModeEnabled: Boolean) {
        if (isDarkModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        // Recreate the activity to apply the new theme immediately
        recreate()
    }

    private fun getThemePreferenceFromSharedPreferences(): Boolean {
        val sharedPreferences: SharedPreferences =
            PreferenceManager.getDefaultSharedPreference(this)
        // "theme_preference" is the key, false is the default value (light mode)
        return sharedPreferences.getBoolean("theme_preference", false)
    }

    // Function to Update theme preference in SharedPreferences
    private fun updateThemePreferenceInSharedPreferences(isDarkEnablled: Boolean) {
        val sharedPreferences: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(this)
        val editor = sharedPreferences.edit()
        // "theme_preference" is the key to store the theme preference
        editor.putBoolean("theme_preference", isDarkModeEnabled)
        editor.apply()
    }
}