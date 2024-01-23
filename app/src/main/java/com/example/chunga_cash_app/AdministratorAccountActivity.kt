package com.example.chunga_cash_app

import android.app.Activity

mport android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.content.Intent
import com.example.chunga_cash_app.databinding.ActivityAdminAccountBinding

class AdministratorAccountActivity : Activity() {

    private lateinit var binding : ActivityAdminAccountBinding
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminAccountBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view) // XML layout file

        val enrollmentAndSavings = findViewById<Button>(R.id.changeDetails)
        val changeDetails = findViewById<Button>(R.id.enrollmentAndSavings)

        // set the buttons On Click Listener
        binding.changeDetails.setOnClickListener {
            // When clicked it opens up 'Change Details Activity'
            // User can change the Admin Data Class details
            val intent = Intent(this, ChangeDetailsActivity::class.java)
            startActivity(intent)
        }

        binding.enrollmentAndSavings.setOnClickListener {
            // When clicked it opens up the 'Enrollment And Savings Activity'
            // User can see total number of Students Enrolled in the app & Total Amount Saved
            val intent = Intent(this, EnrollmentAndSavingsActivity::class.java)
            startActivity(intent)
        }
    }
}