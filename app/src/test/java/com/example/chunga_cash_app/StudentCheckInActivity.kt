package com.example.chunga_cash_app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Toast

class StudentCheckInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_check_in)

        // Retrieve student data from intent
        val studentName = intent.getStringExtra("studentName")
        val admissionNumber = intent.getStringExtra("admissionNumber")
        val className = intent.getStringExtra("className")

        // Display student details
        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        val admissionNumberTextView = findViewById<TextView>(R.id.admissionNumberTextView)
        val classTextView = findViewById<TextView>(R.id.classTextView)
        nameTextView.text = "STUDENT'S NAME: $studentName"
        admissionNumberTextView.text = "ADMISSION NO: $admissionNumber"
        classTextView.text = "CLASS: $className"

        // Section 2: Pin Code EditText and Buttons
        val pinCodeEditText = findViewById<EditText>(R.id.pinCodeEditText)
        val okButton = findViewById<Button>(R.id.okButton)
        val backButton = findViewById<Button>(R.id.backButton)

        okButton.setOnClickListener {
            val enteredPin = pinCodeEditText.text.toString()

            // Check if the entered pin is correct (you need to implement this logic)
            val correctPin = "1234" // Example correct pin for demonstration
            if (enteredPin == correctPin) {
                // If Pin is correct, open Student Account Activity
                val intent = Intent(this, StudentAccountActivity::class.java)
                startActivity(intent)
            } else {
                // Pin is incorrect, show a toast message
                Toast.makeText(this, "! Incorrect Pin Code", Toast.LENGTH_SHORT).show()
            }
        }

        backButton.setOnClickListener {
            // Close this activity and go back to the main activity
            finish()
        }

        // Section 3: Forgot Password Button
        val forgotPasswordButton = findViewById<Button>(R.id.forgotPasswordButton)
        forgotPasswordButton.setOnClickListener {
            // Open StudentForgotPinActivity (you need to define this activity)
            val intent = Intent(this, StudentForgotPinActivity::class.java)
            startActivity(intent)
        }
    }
}