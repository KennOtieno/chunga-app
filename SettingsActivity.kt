import android.os.Bundle
import android.content.Intent
import android.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.laayout_settings_xml) // Change it to the xml layout

        buttonAboutLegal.setOnClickListener { view, ->
            // Opens AboutLegalActivity when cllcked
            startActivity(Intent(this, AboutLegalActivity::class.java))
        }

        buttonAppVersionAndUpdates.setOnClickListener { view, ->
            // Opens AppVersionAndUpdates Activitty when clicked
            startActivity(Intent(this, AppVersion& Updates ::class.java))
        }

        buttonHelpGuides.setOnClickListener { view, ->
            // Opens HelpGuideActivity when clicked
            startActivity(Intent(this, HelpGuideActivity::class.java))
        }

        buttonNotification.setOnListener { view, ->
            // Opens up NotificationPreferenceActivity when clicked by the user
            startActivity(intent(this, NotificationActivity::class.java))
        }

        buttonThemeCustomization.setOnClickListener { view, ->
            // Opens up ThemeCustomizationActivity when clicked by the user
            startActivity(Intent(this, ThemeCustomization::class.java))
        }
    }
}