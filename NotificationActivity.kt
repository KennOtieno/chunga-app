import android.os.Bundle
import androidx.appcomptactivity.app.AppCompatActivity
import android.widget.Button
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.example.chunga_cash_app.PreferencesActivity
import com.example.chunga_cash_app.SoundActivity

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.Layout.activity_notification) // xml layout file

        val preferencesButton = findViewById<Button>(R.id.preferencesButton)
        val soundNotificationButton = findViewById<Button>(R.id.soundNotificationButton)

        preferencesButton.setOnClickListener {
            // Opens preferences activity when clicked
            val intent = (this@NotificationActivity, PreferencesActivity::class.java)
            startActivity(intent)
            // User can select whether he wants to receive updates or Money Notifications(Daraja API)
        }

        soundNotificationButton.setOnClickListener {
            // Opens sound activity when clicked
            val intent = (this@NotificationActivity, SoundActivity::class.java)
            startActivity(intent)
            // User can select his own message ringtone and whether she wants vibration mode
        }
    }
}