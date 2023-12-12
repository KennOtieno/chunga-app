import android.os.Bundle
import android.widget.Spinner
import android.widget.CheckBox
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.media.RingtoneManager
import android.widget.Toast

class SoundActivity : AppCompatActivity() {

    private lateinit var soundSpinner: Spinner
    private lateinit var soundEnabledCheckbox: CheckBox
    private lateinit var vibrationEnabledCheckbox: CheckBox
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound) // XML LAYOUT

        soundSpinner = findViewById(R.id.soundSpinner)
        soundEnabledCheckbox = findViewById(R.id.soundEnabledCheckbox)
        vibrationEnabledCheckbox = findViewById(R.id.vibrationEnabledCheckbox)
        saveButton = findViewById(R.id.saveButton)

        // Load notification sounds from the device's default notification sounds
        val notificationSounds = RingtoneManager.getRingtone(this, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))

        // Get the list of notification sounds available on the device
        val ringtoneManager = RingtoneManager(this)
        ringtoneManager.setType(RingtoneManager.TYPE_NOTIFICATION)
        val cursor = ringtoneManager.cursor

        val soundList = mutableListOf<String>()

        while (cursor.moveToNext()) {
            val notificationTitle = cursor.getString(RingtoneManager.TITLE_COLUMN_INDEX)
            soundList.add(notificationTitle)
        }

        // TODO: Populate the soundSpinner with soundList
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, soundList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        soundSpinner.adapter = adapter

        saveButton.setOnClickListener {
            // Get selected sound from the spinner
            val selectedSound = soundSpinner.selectedItem.toString()

            // Get sound and vibration enabled status
            val isSoundEnabled = soundEnabledCheckbox.isChecked
            val isVibrationEnabled = vibrationEnabledCheckbox.isChecked

            // Save user preferences using SharedPreferences
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
            val editor = sharedPreferences.edit()

            editor.putString("selectedSound", selectedSound) // Save selected sound
            editor.putBoolean("isSoundEnabled", isSoundEnabled) // Save sound enabled status
            editor.putBoolean("isVibrationEnabled", isVibrationEnabled) // Save vibration enabled status

            editor.apply()

            // Toast message since preference is saved
            Toast.makeText(this, "Preference Saved", Toast.LENGTH_SHORT).show()
        }
    }
}