package com.example.chunga_cash_app

import android.os.Bundle
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_app_version_and_updates.*

class AppVersionAndUpdatesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_version_and_updates)

        // Dynamically setting the current app version
        val currentAppVersion = getAppVersion()
        appVersionTextView.text = getString(R.string.app_version, currentAppVersion)

        // Button click listener to navigate to UpdatesActivity
        updateButton.setOnClickListener {
            startActivity(Intent(this, UpdatesActivity::class.java))
        }
    }

    private fun getAppVersion(): String {
        try {
            val pInfo = packageManager.getPackageInfo(packageName, 0)
            return pInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return "N/A"
    }
}