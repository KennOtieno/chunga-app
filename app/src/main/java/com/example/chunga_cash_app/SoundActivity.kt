package com.example.chunga_cash_app

import android.R
import android.os.Bundle
import android.widget.Spinner
import android.widget.CheckBox
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.media.RingtoneManager
import android.preference.PreferenceManager
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.chunga_cash_app.databinding.ActivitySoundBinding

class SoundActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySoundBinding

    private lateinit var soundSpinner: Spinner
    private lateinit var soundEnabledCheckbox: CheckBox
    private lateinit var vibrationEnabledCheckbox: CheckBox
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySoundBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view) // XML LAYOUT

        soundSpinner = binding.soundSpinner
        soundEnabledCheckbox = binding.soundEnabledCheckbox
        vibrationEnabledCheckbox = binding.vibrationEnabledCheckbox
        saveButton = binding.saveButton

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
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, soundList)
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