package com.example.chunga_cash_app

import android.os.Bundle
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.database.*

class PreferencesActivity : AppCompatActivity() {

    // Notification ID for Chunga
    private val NOTIFICATION_ID = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences) // XML layout

        val updatesNotification = findViewById<Switch>(R.id.updatesNotification)

        // Check the initial state of the switch
        if (updatesNotification.isChecked) {
            enableUpdatesNotifications()
        }

        updatesNotification.setOnCheckedChangeListener { _, isChecked ->
            // Handles Updates notifications
            if (isChecked) {
                // Updates Notifications will be enabled & we show a Toast
                enableUpdatesNotifications()
                showToast("Updates Enabled")
            } else {
                // Updates will be disabled, and the user won't receive Updates Notifications
                disableUpdatesNotifications()
                showToast("Updates Disabled")
            }
        }
    }

    private fun showToast(message: String) {
        // Display a Toast message to the user
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun enableUpdatesNotifications() {
        // Show a notification to inform the user about the new update
        val notificationTitle = "New Update Available"
        val notificationMessage = "A new version of Chunga Cash App is available. UPDATE NOW to get new improved features."

        // Call the function to display the notification
        showNotification(notificationTitle, notificationMessage)
    }

    private fun showNotification(title: String, message: String) {
        // Create and display a notification using Android's NotificationManager
        val builder = NotificationCompat.Builder(this, "channel_id")
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // NotificationManager is a system service that manages notifications
        with(NotificationManagerCompat.from(this)) {
            // Use the notification ID to update or cancel the notification later
            notify(NOTIFICATION_ID, builder.build())
        }
    }

    private fun disableUpdatesNotifications() {
        // Logic to disable Updates Notifications if needed
        // You may cancel the existing notification here if needed
        with(NotificationManagerCompat.from(this)) {
            cancel(NOTIFICATION_ID)
        }
    }
}