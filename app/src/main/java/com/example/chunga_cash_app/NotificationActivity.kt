import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.content.Intent
import com.example.chunga_cash_app.PreferencesActivity
import com.example.chunga_cash_app.R
import com.example.chunga_cash_app.SoundActivity
import com.example.chunga_cash_app.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationBinding
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view) // xml layout file

        val preferencesButton = binding.preferencesButton
        val soundNotificationButton = binding.soundNotificationButton

        preferencesButton.setOnClickListener {
            // Opens preferences activity when clicked
            val intent = Intent(this@NotificationActivity, PreferencesActivity::class.java)
            startActivity(intent)
            // User can select whether he wants to receive updates or Money Notifications(Daraja API)
        }

        soundNotificationButton.setOnClickListener {
            // Opens sound activity when clicked
            val intent = Intent(this@NotificationActivity, SoundActivity::class.java)
            startActivity(intent)
            // User can select his own message ringtone and whether she wants vibration mode
        }
    }
}