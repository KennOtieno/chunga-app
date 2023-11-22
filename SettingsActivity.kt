package com.example.chunga_cash_app

import android.os.Bundle
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings) // xml layout

        buttonAboutLegal.setOnClickListener {
            // Opens AboutLegalActivity when cllcked
            startActivity(Intent(this, AboutLegalActivity::class.java))
        }

        buttonAppVersionAndUpdates.setOnClickListener {
            // Opens AppVersionAndUpdates Activity when clicked
            startActivity(Intent(this, AppVersionAndUpdatesActivity ::class.java))
        }

        buttonHelpGuide.setOnClickListener {
            // Opens HelpGuideActivity when clicked
            startActivity(Intent(this, HelpGuideActivity::class.java))
        }

        buttonNotification.setOnClickListener {
            // Opens up NotificationPreferenceActivity when clicked by the user
            startActivity(intent(this, NotificationActivity::class.java))
        }

        buttonThemeCustomization.setOnClickListener {
            // Opens up ThemeCustomizationActivity when clicked by the user
            startActivity(Intent(this, ThemeCustomizationActivity::class.java))
        }
    }
}