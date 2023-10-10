import android.os.Bundle
import android.appcompatactivity.app.AppCompatActivity
import android.widget.Switch

class PreferencesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.Layout.activity_preferences) // XML layout

        val updatesNotification = findViewById<Switch>(R.id.updatesNotification)
        val mpesaNotification = findViewById<Switch>(R.id.mpesaNotification)

        updatesNotification.setOnClickListener {
            // When the switch is clicked it would either turn on or off
            // If on updates notification shall be received if off notifications won't be received
        }

        mpesaNotification.setOnClickListener {
            // When switch is clicked it would either turn on or off
            // If on mpesa notifications shall be received if off notifications won't be received
        }
    }
}