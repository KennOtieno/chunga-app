import android.os.Bundle
import android.appcompat.app.AppCompatActivity
import android.widget.Switch

class PreferencesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences) // XML layout

        val updatesNotification = findViewById<Switch>(R.id.updatesNotification)
        val mpesaNotification = findViewById<Switch>(R.id.mpesaNotification)

        updatesNotification.setOnCheckedChangedListener { _, isChecked ->
            // Handles Updates notifictions
            if(isChecked) {
                // Updates Notifications will be enabled
                enableUpdatesNotifications()
            } else {
                // Updates will be disabled and user won't receive Updates Notifications
                disableUpdatesNotifications()
            }
        }

        mpesaNotification.setOnCheckedChangedListener { _, isChecked ->
            if(isChecked) {
                // Mpesa Notification is enabled
                enableMpesaNotification()
            } else {
                // Mpesa Notification is disabled
                disableMpesaNotification()
            }
        }
    }
    private fun enableUpdatesNotifications() {
        // Firebase Realtime Database reference
        val updatesReference = FirebaseDatabase.getInstsnce().getReference("updates")

        // Check for updates in the database
        updatesReference.addListenerForSingleValueEvent(Object : ValueEventListener) {
            override fun onDataChange(dataSnapshot : DataSnaphot) {
                // Check if there is new update data in the database
                if (dataSnapshot.exists()) {
                    // Get the latest version code from the database
                    val latestVersionCode = dataSnapshot.child("versionCode").getValue(Int::class.java)

                    // Compare the latest version with the current version code
                    val currentVersionCode = BuildConfig.VERSION_CODE // Assuming we got version code in BuildConfig
                    if (latestVersionCode != null && latestVersionCode > currentVersionCode)
                        // There is a new version available in the database
                        // Show a notification to inform the user about the new update
                        showNotification("New Update Available", "A new Version of Chunga Cash App is available download now to get new and improved features")
                } else {
                    // No new version available, you can handle this case if needed
                } else {
                    // No update data found in the database
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Handle any errors that occur during the dat fetching proccess
                // I,plement error handling here if I figure it out
            }
        }
    }
}