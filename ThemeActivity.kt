import android.os.Bundle
import android. appcomapatactivity.app.AppCompatActivity

class ThemeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState : Bundle?)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_theme) // Change to xml layout file

    val themeSwitch = findViewBySwitch<SwitchComapat>(R.id.themeSwitch)

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
        AppCompatDelegate.setDefailtNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
    // Recreate the activity to apply the new theme immediately
    recreate()
}

private fun getThemePreferenceFromSharedPreferences(): Boolean {

}