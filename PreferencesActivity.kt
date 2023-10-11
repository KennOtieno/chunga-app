import android.os.Bundle
import android.appcompatactivity.app.AppCompatActivity
import android.widget.Switch

class PreferencesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.Layout.activity_preferences) // XML layout

        val updatesNotification = findViewById<Switch>(R.id.updatesNotification)
        val mpesaNotification = findViewById<Switch>(R.id.mpesaNotification)

        updatesNotification.setOnCheckedChangedListener { _, isChecked ->
            // Handles Updates notifictions
            if(isChecked) {
                // Updates Notifications will be enabled
                // Logic for this to follow immediately
            } else {
                // Updates will be disabled and user won't receive Updates Notifications
                // Logic for this below
            }
        }

        mpesaNotification.setOnCheckedChangedListener { _, isChecked ->
            // Handles M-PESA notification...this is something we shall write the logic code for it
            // once we have integrated the Daraja API into Chunga App
            if(isChecked) {
                // Mpesa Notification is enabled
                // The code Logic for this to be done once the API is integrated
            } else {
                // Mpesa Notification is disabled
                // The code logic for this to be written once the API is integrated
            }
        }
    }
}