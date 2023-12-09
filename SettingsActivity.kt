package com.example.chunga_cash_app

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.chunga_cash_app.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view) // xml layout

        binding.buttonAboutLegal.setOnClickListener {
            // Opens AboutLegalActivity when cllcked
            startActivity(Intent(this, AboutLegalActivity::class.java))
        }

        binding.buttonAppVersionAndUpdates.setOnClickListener {
            // Opens AppVersionAndUpdates Activity when clicked
            startActivity(Intent(this, AppVersionAndUpdatesActivity ::class.java))
        }

        binding.buttonHelpGuide.setOnClickListener {
            // Opens HelpGuideActivity when clicked
            startActivity(Intent(this, HelpGuideActivity::class.java))
        }

        binding.buttonNotification.setOnClickListener {
            // Opens up NotificationPreferenceActivity when clicked by the user
            startActivity(Intent(this, NotificationActivity::class.java))
        }

        binding.buttonThemeCustomization.setOnClickListener {
            // Opens up ThemeCustomizationActivity when clicked by the user
            startActivity(Intent(this, ThemeCustomizationActivity::class.java))
        }
    }
}