import android.os.Bundle
import android.appcompatactivity.app.AppCompatActivity
import android.widget.Checkbox
import android.widget.Spinner
import android.widget.Switch

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState : Bundle?)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_notifications) // xml layout

    val pushNotificaionSwitch = findViewById<Switch>(R.id.pushNotificationSwitch)
    val notificationSoundSpinner = findViewById<Switch>(R.id.notificationSoundSpinner)
    val messageNotificationCheckBox = findViewById<Switch>(R.id.messageNotificationCheckBox)
    val UpdateNotificationCheckBox = findViewById<Switch>(R.id.UpdateNotificationCheckBox)
}