import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.CheckBox
import android.widget.Spinner
import android.widget.Switch

class NotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification) // xml layout

        val pushNotificationSwitch = findViewById<Switch>(R.id.pushNotificationSwitch)
        val notificationSoundSpinner = findViewById<Spinner>(R.id.notificationSoundSpinner)
        val messageNotificationCheckBox = findViewById<CheckBox>(R.id.messageNotificationCheckBox)
        val updateNotificationCheckBox = findViewById<CheckBox>(R.id.updateNotificationCheckBox)

        // Load notification settings from SharedPreferences and set UI elements accordingly
        // ...

        // Listen for changes in UI elements and update notification preferences accordingly
        pushNotificationSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Handle push notification enable/disable
            // Update corresponding SharedPreferences value
        }

        notificationSoundSpinner.setOnItemSelectedListener { /* Handle selected sound */ }

        messageNotificationCheckBox.setOnCheckedChangeListener { _, isChecked ->
            // Handle message notification preference change
            // Update corresponding SharedPreferences value
        }

        updateNotificationCheckBox.setOnCheckedChangeListener { _, isChecked ->
            // Handle update notification preference change
            // Update corresponding SharedPreferences value
        }
        // Add listeners for other notification preference checkboxes as needed
    }
}