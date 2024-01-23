package com.example.chunga_cash_app

import android.os.Bundle
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.chunga_cash_app.databinding.ActivityAppVersionAndUpdatesBinding
//import kotlinx.android.synthetic.main.activity_app_version_and_updates.*

class AppVersionAndUpdatesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAppVersionAndUpdatesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppVersionAndUpdatesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Dynamically setting the current app version
        val currentAppVersion = getAppVersion()
        binding.appVersionTextView.text = getString(binding.app_version, currentAppVersion)

        // Button click listener to navigate to UpdatesActivity
        binding.updateButton.setOnClickListener {
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