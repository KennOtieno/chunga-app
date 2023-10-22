import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class PreferencesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences) // XML layout

        val updatesNotification = findViewById<Switch>(R.id.updatesNotification)
        val mpesaNotification = findViewById<Switch>(R.id.mpesaNotification)

        updatesNotification.setOnCheckedChangeListener { _, isChecked ->
            // Handles Updates notifications
            if (isChecked) {
                // Updates Notifications will be enabled
                enableUpdatesNotifications()
            } else {
                // Updates will be disabled, and the user won't receive Updates Notifications
                disableUpdatesNotifications()
            }
        }

        mpesaNotification.setOnCheckedChangeListener { _, isChecked ->
            // Handles Mpesa notifications
            if (isChecked) {
                // Mpesa Notification is enabled
                enableMpesaNotifications()
            } else {
                // Mpesa Notification is disabled
                disableMpesaNotifications()
            }
        }
    }

    private fun enableUpdatesNotifications() {
        // Firebase Realtime Database reference
        val updatesReference = FirebaseDatabase.getInstance().getReference("updates")

        // Check for updates in the database
        updatesReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Check if there is new update data in the database
                if (dataSnapshot.exists()) {
                    // Get the latest version code from the database
                    val latestVersionCode = dataSnapshot.child("versionCode").getValue(Int::class.java)

                    // Compare the latest version with the current version code
                    val currentVersionCode = BuildConfig.VERSION_CODE // Assuming we got version code in BuildConfig
                    if (latestVersionCode != null && latestVersionCode > currentVersionCode) {
                        // There is a new version available in the database
                        // Show a notification to inform the user about the new update
                        showNotification("New Update Available", "A new version of Chunga Cash App is available. Download now to get new and improved features.")
                    } else {
                        // No new version available, you can handle this case if needed
                    }
                } else {
                    // No update data found in the database
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle any errors that occur during the data fetching process
                // Implement error handling here if needed
            }
        })
    }

    private fun showNotification(title: String, message: String) {
        // Implement code to create and display a notification using Android's NotificationManager
        // This function would typically involve creating a NotificationCompat.Builder and displaying the notification
        // Implement the necessary code here for showing the notification to the user
    }

    private fun disableUpdatesNotifications() {
        // Logic to disable Updates Notifications if needed
    }

    private fun enableMpesaNotifications() {
        // Logic to enable Mpesa Notifications if needed
    }

    private fun disableMpesaNotifications() {
        // Logic to disable Mpesa Notifications if needed
    }
}