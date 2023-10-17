import android.os.Bundle
import android.appcomptactivity.app.AppCompatActivity
import android.widget.Button
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.Layout.activity_notification) // xml layout file

        val preferencesButton = findViewById<Button>(R.id.preferencesButton)
        val soundNotificaticationButton = findViewById<Button>(R.id.soundNotificationButton)

        preferencesButton.setOnClickListener {
            // Opens preferences activity when clicked
            val intent (this@NotificationActivity, PreferenceActivity::class.java)
            startActivity(intent)
            // User can select whether he wants to receive updates or Money Notifications(Daraja API)
        }

        soundNotificaticationButton.setONClickListener {
            // Opens sound activity when clicked
            val intent (this@NotificationActivity, SoundActivity::class.java)
            startActivity(intent)
            // User can seect his own message ringtone and whether she wants vibration mode
        }
    }
}