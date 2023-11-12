package com.example.chunga_cash_app

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.content.Intent
import com.example.chunga_cash_app.databinding.ActivityAdminAccountBinding

class AdministrationActivity : Activity() {

    private lateinit var binding : ActivityAdminAccountBinding
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminAccountBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view) // XML layout file

//        val enrollmentSavings = findViewById<Button>(R.id.enrollmentSavings)
//        val changeDetails = findViewById<Button>(R.id.changeDetails)
//        val transactionDetails = findViewById<Button>(R.id.transactionDetails)

        // set the buttons On Click Listener
        binding.enrollmentSavings.setOnClickListener {
            // When clicked it opens up the Enrollment and Savings Activity
            // User can see total number of students enrolled in the app & Total Amount saved
            val intent = Intent(this, EnrollmentSavingsActivity::class.java)
            startActivity(intent)
        }

        binding.changeDetails.setOnClickListener {
            // When clicked it opens up Change Details Activity
            // You can change the Admin class details
//            val intent = Intent(this, ChangeDetailsActivity::class.java)
            startActivity(intent)
        }

        binding.transactionDetails.setOnClickListener {
            // When clicked Transaction Activity
            // You can see accounted details & unaccounted MPESAA details
//            val intent = Intent(this, TransactionDetailsActivity::class.java)
            startActivity(intent)
        }
    }
}